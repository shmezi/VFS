package lol.vfs.model.users

import lol.vfs.requests.UserRequest
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents a user that can log into the system
 * @param id ID of the user
 * @param pwd The stored password hash of the user
 * @param name The first name of the user
 * @param lastName The last name of the user
 * @param type The [UserType] of the user
 * @param validTokens The tokens that are stored for a user that allow authentication
 */
data class User(
   @BsonId
   val id: String,
   var pwd: String,
   var name: String,
   var lastName: String,
   var type: UserType,
   val validTokens: MutableList<String>
) {
   /**
    * The public representation of the user in a [UserRequest]
    * @return The [UserRequest] of the [User]
    */
   fun public() = UserRequest(id, name, lastName, type)
}