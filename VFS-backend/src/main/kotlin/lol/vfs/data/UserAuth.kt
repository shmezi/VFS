package lol.vfs.data

import lol.vfs.AuthValidation.bakeCookie
import lol.vfs.AuthValidation.checkPassword
import lol.vfs.model.users.User
import lol.vfs.model.users.UserType
import lol.vfs.requests.LoginRequest
import lol.vfs.requests.RegisterRequest
import lol.vfs.routing.UserSession
import org.mindrot.jbcrypt.BCrypt

object UserAuth {
   val users = Database.userDB

   /**
    * Check if a user session is still authenticated
    */
   suspend fun UserSession.authenticated(): Boolean {
      return users.get(id)?.validTokens?.contains(token) ?: false
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

      val user = users.get(id) ?: return null

      val valid = checkPassword(user.pwd, password)
      if (!valid) return null

      val cookie = bakeCookie()
      user.validTokens.add(cookie)
      return cookie
   }

   /**
    *  Register a new user into the system
    */
   suspend fun RegisterRequest.register(): User? {
      if (users.get(id) != null) return null
      val user = this

      when (user.type) {
         UserType.ADMIN -> Database.adminDB
         UserType.PARENT -> Database.parentDB
         UserType.DOCTOR -> Database.doctorDB
      }.getOrDefault(user.id) {}

      return users.getOrDefault(id) {
         pwd = BCrypt.hashpw(user.password, BCrypt.gensalt())
         name = user.name
         lastName = user.lastName
         type = user.type

      }

   }
}