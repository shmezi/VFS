package lol.vfs.model.organizational

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Grade(
   @SerialName("_id")
   val id: String,
   val prettyPrint:String,
   val classes: MutableSet<Class>
)