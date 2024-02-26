package lol.ezra

import kotlinx.serialization.SerialName

data class Class(
   @SerialName("_id")
   val id: String,
   val students: MutableSet<Student>
) {
   var selected = false

}