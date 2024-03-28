package lol.vfs.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Doctor(
   @BsonId
   val id: String,
   val grades: MutableSet<String>
) {

}