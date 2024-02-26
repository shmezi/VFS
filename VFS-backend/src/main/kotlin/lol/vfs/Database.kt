package lol.vfs

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.firstOrNull
import lol.vfs.db.UserType
import lol.vfs.db.*
import lol.vfs.minilib.pq
import lol.vfs.utils.randomString
import org.mindrot.jbcrypt.BCrypt


object Database {
   private val mongo = MongoClient.create("mongodb://localhost:27017").getDatabase("VFS")
   private val userDB = mongo.getCollection<User>("users")
   private val classDB = mongo.getCollection<Class>("classes")
   private val teacherDb = mongo.getCollection<Teacher>("teachers")
   private val parentDb = mongo.getCollection<Parent>("parents")
   private val studentDb = mongo.getCollection<Student>("students")
   private val testDb = mongo.getCollection<MedTest>("tests")


//:::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//                     Class Database                     //
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::://

   suspend fun getClass(classId: String) = classDB.find(eq("_id", classId)).firstOrNull()
   suspend fun getParent(parentId: String) = parentDb.find(eq("_id", parentId)).firstOrNull()
   suspend fun getStudent(studentId: String) = studentDb.find(eq("_id", studentId)).firstOrNull()

   suspend fun getTeacher(teacherId: String) = teacherDb.find(eq("_id", teacherId)).firstOrNull()

   suspend fun getTest(testId: String) = testDb.find(eq("_id", testId)).firstOrNull()


//:::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//                    Database system                     //
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::://

   private val toUpdate = mutableMapOf<String, User>()
   suspend fun runDBUpdate() {
      toUpdate.forEach {
         userDB.replaceOne(eq(User::id.name, it.key), it.value)
      }
   }


   //:::::::::::::::::::::::::::::::::::::::::::::::::::::::://
//                     User Database                      //
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::://
   private fun bakeCookie() = randomString(16)
   private val validUserPWD = "^[a-zA-Z0-9 ]*\$".toRegex()

   /**
    * Validate a user / password for length and charset
    * @param min Minimum size for value
    * @param max Maximum value of value
    * @param value The value to make checks upon
    * @return If the value followed the given rules
    */
   private fun validate(min: Int, max: Int, value: String): Boolean {
      if (value.length < min || value.length > max) return false
      if (!value.matches(validUserPWD)) return false
      return true
   }


   /**
    * A valid password is a password consisting of purely chars and nums.
    * Passwords must be above 7 and bellow 32 chars long.
    * @param password The password to validate
    * @return Weather the password is valid
    */
   private fun validatePassword(password: String) = validate(7, 32, password)

   /**
    * A valid username is a username consisting of purely chars and nums.
    * Usernames must be above 4 and bellow 16 chars long.
    * @param username The password to validate
    * @return Weather the password is valid
    */
   private fun validateUsername(username: String) = validate(4, 16, username)

   /**
    * Check a password against its hash
    * @param hash Hashed password
    * @param password Password to be checked
    */
   private fun checkPassword(hash: String, password: String): Boolean {
      if (!validatePassword(password)) return false
      return BCrypt.checkpw(password, hash)
   }

   /**
    * Check if a token is valid
    * @param user UserID of user
    * @param token Token to check
    */

   suspend fun checkToken(user: String, token: String): Boolean {
      val valid = (userDB.find(eq("_id", user)).firstOrNull() ?: return false).validTokens
      return valid.contains(token)

   }

   /**
    * Create a new user
    * @param id National ID of the user
    * @param name Name of the user
    * @param lastName LastName of the user
    * @param image Profile picture of user
    * @param password Password Hash (Using [BCrypt])
    * @param roles The roles the user performs
    */
   suspend fun createUser(
      id: String,
      name: String,
      lastName: String,
      image: String,
      password: String,
      vararg roles: UserType
   ): User {
      val salt = BCrypt.gensalt()
      val user =
         User(id, BCrypt.hashpw(password, salt), salt, name, lastName, image, mutableListOf(), roles.toMutableSet())
      userDB.insertOne(user)
      roles.forEach {
         when (it) {
            UserType.ADMIN -> teacherDb.insertOne(Teacher(id, mutableSetOf()))
            UserType.PARENT -> parentDb.insertOne(Parent(id, mutableSetOf()))
            UserType.DOCTOR -> TODO()
         }
      }

      return user
   }

   /**
    * Replaces a user in the database
    * @param user The user to replace
    */
   private suspend fun replaceUser(user: User) = userDB.replaceOne(eq("_id", user.id), user)

   /**
    * Get a user given ID and token
    * @param id National ID of user
    * @param token Access token of user
    * @return The user of [id] if [token] was valid.
    */
   suspend fun getUser(id: String, token: String): User? {
      val user = userDB.find<User>(eq("_id", id)).firstOrNull() ?: return null
      return if (user.validTokens.contains(token)) user else null
   }

   suspend fun userExist(id: String) = userDB.find(eq("_id", id)).firstOrNull() != null

   /**
    * Generate a new token for given id and password
    * @param id National ID of the user
    * @param pwd Password of the user
    * @return If username and password match an access token is returned otherwise null is returned
    */
   suspend fun newToken(id: String?, pwd: String?): String? {
      id ?: return null
      pwd ?: return null
      id.pq()
      pwd.pq()
      val user = userDB.find(eq("_id", id)).firstOrNull().pq() ?: return null
      if (!checkPassword(user.pwd, pwd)) return null

      val token = bakeCookie()
      user.validTokens.add(token)
      replaceUser(user)

      return token
   }

   suspend fun logout(session: LoginSession?) {
      session ?: return
      val u = getUser(session.username, session.token) ?: return
      u.validTokens.remove(session.token)
      replaceUser(u)
   }

}

