package lol.vfs.db.testing

import kotlinx.serialization.Serializable

/**
 * A treatment result of a student
 * @param approved Weather this treatment has been approved for the student
 * @param afterEffects The doctor's notes of the after effects the treatment has had on the student
 */
@Serializable
data class TreatmentData(
   var approved: Boolean = false,
   var afterEffects: String? = null
) {
   /**
    * Weather the treatment has been done on the kid or not
    * @return Weather the student has had the treatment
    */
   fun isComplete() = afterEffects != null
}