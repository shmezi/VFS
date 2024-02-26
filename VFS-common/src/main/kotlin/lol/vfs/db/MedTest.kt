package lol.vfs.db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A medical test that can occur at a school
 */
@Serializable
data class MedTest(
   @SerialName("_id")
   val name: String,
   val description: String
)