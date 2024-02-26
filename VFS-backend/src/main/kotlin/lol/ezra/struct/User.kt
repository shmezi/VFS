package lol.ezra.struct

import lol.ezra.PublicUser
import lol.ezra.UserType
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents a user that can log in to the system
 * This data is used both inside the browser as a cookie.
 * @param id User's personal national ID
 * @param pwd User's Hashed password
 * @param validTokens Mapping of token to IP
 * @param userTypes The different roles a user has
 */
data class User(
   @BsonId
   val id: String,
   val pwd: String,
   val salt: String,
   var name: String,
   var lastName: String,
   var image: String,
   val validTokens: MutableList<String>,
   val userTypes: MutableSet<UserType>
) {
   fun public() = PublicUser(id, name, lastName, image, userTypes)
}