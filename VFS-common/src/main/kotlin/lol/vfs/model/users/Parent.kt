package lol.vfs.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
/**
 * Represents the data stored of a [UserType.PARENT] in the system
 * @param id ID of the parent
 * @param kids The kids that are assigned to the parent
 */
@Serializable
data class Parent(
   @BsonId
   val id: String,
   val kids: MutableSet<String>
)