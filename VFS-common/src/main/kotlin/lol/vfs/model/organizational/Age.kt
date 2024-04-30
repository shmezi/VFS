package lol.vfs.model.organizational

import lol.vfs.lib.Date
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria
import lol.vfs.utils.schoolBirth

/**
 * Defines the ages of a grade
 */
enum class Age(val value: Int) {
   /**
    * First Grade
    */
   FIRST(1),

   /**
    * Second Grade
    */

   SECOND(2),

   /**
    * Third Grade
    */
   THIRD(3),

   /**
    * Forth Grade
    */
   FORTH(4),

   /**
    * Fifth Grade
    */
   FIFTH(5),

   /**
    * Sixth Grade
    */
   SIXTH(6),

   /**
    * Seventh Grade
    */
   SEVENTH(7),

   /**
    * Eighth Grade
    */
   EIGHTH(8),

   /**
    * Ninth Grade
    */

   NINTH(9),

   /**
    * Tenth Grade
    */
   TENTH(10),

   /**
    * Eleventh Grade
    */
   ELEVENTH(11),

   /**
    * Twelveth Grade
    */
   TWELVETH(12),

   /**
    * After school
    */
   AFTER(13),

   /**
    * Before school
    */
   BEFORE(1);

   fun gimatria() = value.toGimatria()

   /**
    * Pretty print version of the year of the grade in gimatria.
    * @return The pretty print version of the school year in gimatria
    */
   fun gradePrettyPrint() = "כיתה" + " " + gimatria()

   companion object {
      fun Int.toAge(): Age {
         val s = (Date.today() - this) + 1
         if (s > 12) return AFTER
         if (s < 1) return BEFORE
         return entries[s - 1]
      }
   }
}