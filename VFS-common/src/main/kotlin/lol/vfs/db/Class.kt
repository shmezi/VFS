package lol.vfs.db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Class(
   @SerialName("_id")
   val id: String,
   val prettyPrint: String,
   val students: MutableSet<Student>
) {

}