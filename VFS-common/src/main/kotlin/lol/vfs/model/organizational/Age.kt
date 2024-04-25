package lol.vfs.model.organizational

import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria

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
   AFTER(12),

   /**
    * Before school
    */
   BEFORE(1);

   fun gimatria() = this.value.toGimatria()

}