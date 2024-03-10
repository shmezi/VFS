package lol.vfs.model.organizational

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lol.vfs.model.users.Student

@Serializable
data class Class(
   @SerialName("_id")
   val id: String,
   val prettyPrint: String,
   val students: MutableSet<Student>
)