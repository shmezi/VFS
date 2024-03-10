package lol.vfs.model.testing

import kotlinx.serialization.Serializable
import lol.vfs.minilib.Date

/**
 * A treatment result of a student
 * @param approved Weather this treatment has been approved for the student
 * @param afterEffects The doctor's notes of the after effects the treatment has had on the student
 */
@Serializable
data class TreatmentData(
   var approved: Boolean = false,
   var afterEffects: String? = null,
   var date: Date? = null
) {
   /**
    * Weather the treatment has been done on the kid or not
    * @return Weather the student has had the treatment
    */
   fun isComplete() = afterEffects != null
}