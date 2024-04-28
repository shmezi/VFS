package lol.vfs.model.medical

import kotlinx.serialization.Serializable
import lol.vfs.lib.Date

/**
 * The test data of a student
 * @param id The ID of the [Medical] that this data refers to.
 * @param approved Weather this test is approved by the parent for the student
 * @param results The results the doctor has given regarding the test
 * @param recommendations The doctor's recommendations regarding the test
 */
@Serializable
data class TestData(
   val id: String,
   var approved: Boolean = false,
   var results: String? = null,
   var recommendations: String? = null
) {
   /**
    * Weather the test has been run on the kid or not
    * @return Weather the student has had the test
    */
   fun isComplete() = results != null
}