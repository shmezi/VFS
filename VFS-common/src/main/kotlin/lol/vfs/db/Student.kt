package lol.vfs.db

import kotlinx.serialization.SerialName

/**
 * Represents a student at the school
 * @param id National Id of student
 * @param name The name of the student
 * @param lastName The last name of the student
 * @param medicalTests The tests student has gone through TestID | Test Result
 */
data class Student(
   @SerialName("_id")
   val id: String,
   val name: String,
   val lastName: String,
   val medicalTests: MutableMap<String, String?>

) {


}