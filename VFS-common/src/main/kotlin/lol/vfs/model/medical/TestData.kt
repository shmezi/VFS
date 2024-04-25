package lol.vfs.model.medical

import kotlinx.serialization.Serializable
import lol.vfs.lib.Date

/**
 * The test data of a student
 * @param approved Weather this test is approved by the parent for the student
 * @param results The results the doctor has given regarding the test
 * @param recommendations The doctor's recommendations regarding the test
 * @param date The date that the test will be run on
 */
@Serializable
data class TestData(
   var approved: Boolean = false,
   var results: String? = null,
   var recommendations: String? = null,
   var date: Date? = null
) {
   /**
    * Weather the test has been run on the kid or not
    * @return Weather the student has had the test
    */
   fun isComplete() = results != null
}