package lol.vfs.model.users

import lol.vfs.requests.UserRequest
import org.bson.codecs.pojo.annotations.BsonId

/** TODO: Rewrite docs
 * Represents a user that can log in to the system
 * This data is used both inside the browser as a cookie.
 * @param id User's personal national ID
 * @param pwd User's Hashed password
 * @param validTokens Mapping of token to IP
 * @param type The different roles a user has
 */

data class User(
   @BsonId
   val id: String,
   var pwd: String,
   var name: String,
   var lastName: String,
   var image: String,
   val validTokens: MutableList<String>,
   var type: UserType
) {
   fun public() = UserRequest(id, name, lastName, type)
}