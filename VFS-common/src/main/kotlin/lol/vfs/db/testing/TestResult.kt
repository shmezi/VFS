package lol.vfs.db.testing

import kotlinx.serialization.Serializable
import lol.vfs.minilib.Date

/**
 * A test result of a student
 * @param approved Weather this test has been approved for the student
 * @param results The results the doctor has given regarding the test
 * @param recommendations The doctor's recommendations regarding the test
 */
@Serializable
data class TestResult(
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