package lol.vfs.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents the data stored of an [UserType.DOCTOR] in the system
 * @param id ID of the Doctor
 * @param grades The grades that are assigned to the doctor
 */
@Serializable
data class Doctor(
   @BsonId
   val id: String,
   val grades: MutableSet<Int>
) {

}