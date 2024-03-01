package lol.vfs.db.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lol.vfs.db.testing.TestResult
import lol.vfs.db.testing.TreatmentData

/**
 * Represents a student at the school
 * @param id National Id of student
 * @param name The name of the student
 * @param lastName The last name of the student
 * @param tests The tests the student has been assigned to do / have done
 * @param treatments The treatments the student has been assigned to have / have done
 */
@Serializable
data class Student(
   @SerialName("_id")
   val id: String,
   val name: String,
   val lastName: String,
   val tests: MutableMap<String, TestResult>,
   val treatments: MutableMap<String, TreatmentData>
) {

}