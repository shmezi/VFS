package lol.vfs.model.testing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A medical is a test or treatment that can be assigned to students, grades etc.
 * @param name Name of test / treatment
 * @param description A short description regarding the test / treatment
 */
@Serializable
data class Medical(
   @SerialName("_id")
   val name: String,
   val description: String,
) {
   // TODO TODO: make this include the type it is.
}