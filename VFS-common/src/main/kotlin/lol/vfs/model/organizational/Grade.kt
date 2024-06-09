package lol.vfs.model.organizational

import kotlinx.serialization.Serializable
import lol.vfs.lib.Date
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria
import lol.vfs.model.organizational.Age.Companion.toAge
import lol.vfs.utils.schoolBirth
import org.bson.codecs.pojo.annotations.BsonId

/**
 * Represents a grade in the database that is directly transferred from the frontend to the backend
 * @param id The id of the grade
 * @param startYear The year that the grade started at the school
 * @param classes The classes assigned to the grade, stored based on [ID, Class]
 */
@Serializable
data class Grade(
   @BsonId
   val id: Int,
   val classes: MutableMap<String, Class>,
   val medicals: MutableMap<String, Date> = mutableMapOf()
) {

   /**
    * Calculates the age of the students
    * @return The age that the students are currently at
    */
   fun getAge() = id.toAge()

   /**
    * Gets all the classes assigned to the grade
    */
   fun getClasses() = classes.values

   /**
    * Gets all the students assigned to the grade
    * @return A list of the IDs of the students
    */
   fun getStudents(): List<String> {
      val students = mutableListOf<String>()
      for (clazz in getClasses()) {
         students.addAll(clazz.students)
      }
      return students
   }


   /**
    * Pretty print version of the year of the grade in gimatria.
    * @return The pretty print version of the school year in gimatria
    */
   fun prettyPrint() = "מחזור" + " " + (id - schoolBirth + 1).toGimatria()
}