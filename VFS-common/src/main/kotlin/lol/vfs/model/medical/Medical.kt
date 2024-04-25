package lol.vfs.model.medical

import kotlinx.serialization.Serializable
import lol.vfs.lib.db.Id
import lol.vfs.model.organizational.Age
import org.bson.codecs.pojo.annotations.BsonId

/**
 * A medical is a test or treatment that can be assigned to students, grades etc.
 * @param name Name of test / treatment
 * @param type The type of test / treatment that can be assigned
 * @param grade The age that this medical is relevant for
 */
@Serializable
data class Medical(
   @BsonId
   @Id
   val name: String,
   val type: MedicalType,
   val grade: Age
) {
}