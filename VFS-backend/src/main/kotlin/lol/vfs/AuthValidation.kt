package lol.vfs


import org.mindrot.jbcrypt.BCrypt

object AuthValidation {

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
   fun checkPassword(hash: String, password: String): Boolean {
      return BCrypt.checkpw(password, hash)
   }
}