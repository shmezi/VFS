package lol.vfs.db

import kotlinx.serialization.SerialName

data class Teacher(
   @SerialName("_id") val id: String,
   val classes: MutableSet<String>
) {

}