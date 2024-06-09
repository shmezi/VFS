package lol.vfs.data

import lol.vfs.AuthValidation.checkPassword
import lol.vfs.model.users.User
import lol.vfs.model.users.UserType
import lol.vfs.requests.LoginRequest
import lol.vfs.requests.RegisterRequest
import lol.vfs.routing.UserSession
import lol.vfs.utils.bakeCookie
import org.mindrot.jbcrypt.BCrypt

object UserAuth {
   val users = Database.userDB

   /**
    * Check if a user session is still authenticated
    */
   suspend fun UserSession.authenticated(): Boolean {
      return users.get(id)?.validTokens?.contains(token) ?: false
   }

   suspend fun type(id:String) : UserType?{
      return users.get(id)?.type
   }

   /**
    * Destroy a user session
    */
   suspend fun UserSession.destroy(): Boolean {
      return users.get(id)?.validTokens?.remove(token) ?: false
   }

   /**
    * Generate a new token if the [LoginRequest] credentials are correct
    */
   suspend fun LoginRequest.attemptLogin(): String? {

      val user = users.get(id) ?: return null //Get user from database

      //Hash the entered password with salt and check if it is the same as the stored password
      val valid = checkPassword(user.pwd, password)

      if (!valid) return null

      val cookie = bakeCookie() //Generate the token
      user.validTokens.add(cookie) //Add token to valid token list
      return cookie //Return the token
   }

   /**
    *  Register a new user into the system
    */
   suspend fun RegisterRequest.register(ids: List<String>? = null): User? {
      if (users.get(id) != null) return null
      val user = this

      when (user.type) {
         UserType.ADMIN -> {
            val newGrades = ids?.map { it.toInt() }
            Database.adminDB.getOrDefault(user.id) {
               grades.addAll(newGrades ?: return@getOrDefault)
            }
         }

         UserType.PARENT -> {
            Database.parentDB.getOrDefault(user.id) {
               kids.addAll(ids ?: return@getOrDefault)
            }
         }
         UserType.DOCTOR -> {
            val newGrades = ids?.map { it.toInt() }
            Database.doctorDB.getOrDefault(user.id) {
               grades.addAll(newGrades ?: return@getOrDefault)
            }
         }
      }

      return users.getOrDefault(id) {
         pwd = BCrypt.hashpw(user.password, BCrypt.gensalt())
         name = user.name
         lastName = user.lastName
         type = user.type

      }

   }
}