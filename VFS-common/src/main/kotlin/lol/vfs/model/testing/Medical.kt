package lol.vfs.model.testing

import kotlinx.serialization.Serializable
import lol.vfs.lib.db.Id
import lol.vfs.model.organizational.Age
import org.bson.codecs.pojo.annotations.BsonId

/**
 * A medical is a test or treatment that can be assigned to students, grades etc.
 * @param name Name of test / treatment
 * @param description A short description regarding the test / treatment
 */
@Serializable
data class Medical(
  @BsonId
   val name: String,
   val description: String,
   val type: MedicalType,
   val grades: Age
) {
   // TODO TODO: make this include the type it is.
}