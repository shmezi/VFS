package lol.vfs.model.medical.learning

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The data parents can read in the learning center
 * @param id ID of the learning material
 * @param topic The topic the material regards
 * @param shortDescription A shortened description of the material
 * @param image The image path of the material (Currently must be compiled in :( )
 */
@Serializable
data class LearningMaterial(
   @SerialName("_id")
   val id: String,
   val topic: String,
   val shortDescription: String,
   val description: String,
   val image: String,
)