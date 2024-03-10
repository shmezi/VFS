package lol.vfs.model.users

import kotlinx.serialization.SerialName
import lol.vfs.model.organizational.Class

data class Teacher(
   @SerialName("_id") val id: String,
   val classes: MutableSet<Class>
) {

}