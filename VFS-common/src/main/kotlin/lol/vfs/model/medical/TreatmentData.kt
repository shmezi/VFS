package lol.vfs.model.medical

import kotlinx.serialization.Serializable
import lol.vfs.lib.Date

/**
 * The treatment data of a student
 * @param id The ID of the [Medical] that this data refers to.
 * @param approved Weather this treatment is approved by the parent for the student.
 * @param afterEffects The doctor-notes of the after effects the treatment has had on the student.
 */
@Serializable
data class TreatmentData(
   val id: String,
   var approved: Boolean = false,
   var afterEffects: String? = null) {
   /**
    * Weather the treatment is done on the kid or not
    * @return Weather the student has had the treatment
    */
   fun isComplete() = afterEffects != null
}