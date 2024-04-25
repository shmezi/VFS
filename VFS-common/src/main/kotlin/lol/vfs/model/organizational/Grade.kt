package lol.vfs.model.organizational

import kotlinx.serialization.Serializable
import lol.vfs.lib.Date
import lol.vfs.lib.db.Database
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria
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
   var startYear: Int,
   var classes: MutableMap<String, Class>
) {
   /**
    * Calculates the age of the students
    * @return The age that the students are currently at
    */
   fun getAge(): Age {
      val s = Date.today() - startYear
      if (s > 12) return Age.AFTER
      if (s < 1) return Age.BEFORE
      return Age.entries[s]
   }

   /**
    * Pretty print version of the year of the grade in gimatria.
    * @return The pretty print version of the school year in gimatria
    */
   fun prettyPrint() = "מחזור" + " " + (startYear - schoolBirth + 1).toGimatria()
}