package lol.vfs.model.users

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents the data stored of an [UserType.ADMIN] in the system
 * @param id ID of the admin
 * @param grades The grades that are assigned to the admin
 */
@Serializable
data class Admin(
   @BsonId
   val id: String,
   val grades: MutableSet<Int>
) {
}