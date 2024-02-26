package lol.vfs.db

import kotlinx.serialization.SerialName

data class Class(
   @SerialName("_id")
   val id: String,
   val students: MutableSet<Student>
) {

}