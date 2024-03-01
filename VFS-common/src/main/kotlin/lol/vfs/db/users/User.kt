package lol.vfs.db.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lol.vfs.requests.UserRequest

/** TODO: Rewrite docs
 * Represents a user that can log in to the system
 * This data is used both inside the browser as a cookie.
 * @param id User's personal national ID
 * @param pwd User's Hashed password
 * @param validTokens Mapping of token to IP
 * @param userTypes The different roles a user has
 */
@Serializable
data class User(
   @SerialName("_id")
   val id: String,
   val pwd: String,
   val salt: String,
   var name: String,
   var lastName: String,
   var image: String,
   val validTokens: MutableList<String>,
   val userTypes: UserType
) {
   fun public() = UserRequest(id, name, lastName, userTypes)
}