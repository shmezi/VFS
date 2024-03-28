package lol.vfs.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lol.vfs.model.testing.TestResult
import lol.vfs.model.testing.TreatmentData
import org.bson.codecs.pojo.annotations.BsonId

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
   @BsonId
   val id: String,
   var name: String,
   var grade: String,
   var clazz: String,
   var lastName: String,
   var tests: MutableMap<String, TestResult>,
   var treatments: MutableMap<String, TreatmentData>
)