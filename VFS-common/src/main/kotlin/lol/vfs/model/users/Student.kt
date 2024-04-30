package lol.vfs.model.users

import kotlinx.serialization.Serializable
import lol.vfs.model.medical.TestData
import lol.vfs.model.medical.TreatmentData
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents a student at the school
 * @param id National Id of student
 * @param name The name of the student
 * @param lastName The last name of the student
 * @param startYear The gradeID the student is in
 * @param clazz The classID the student is in
 * @param tests The tests the student has been assigned to do / have done
 * @param treatments The treatments the student has been assigned to have / have done
 */
@Serializable
data class Student(
   @BsonId val id: String,
   var name: String,
   var lastName: String,
   var startYear: Int,
   var clazz: Int,
   var tests: MutableMap<String, TestData>,
   var treatments: MutableMap<String, TreatmentData>
) {
   /**
    * Approve all the treatments and tests
    */
   fun approveAll() {
      tests.values.forEach {
         it.approved = true
      }
      treatments.values.forEach {
         it.approved = true
      }
   }

}