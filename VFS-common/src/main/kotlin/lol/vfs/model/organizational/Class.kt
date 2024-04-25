package lol.vfs.model.organizational

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a class that is stored under a [Grade]
 * @param id The id of the class
 * @param students The students assigned to the class
 */
@Serializable
data class Class(
   @SerialName("_id")
   val id: Int,
   val grade: Int,
   val students: MutableSet<String>
)