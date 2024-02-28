package lol.vfs.utils

import lol.vfs.db.Class
import lol.vfs.db.Grade
import lol.vfs.db.Student

/**
 * Sample data set
 */


fun ffirst(): String {
   val names = listOf(
      "אמה", "ליאם", "אוליביה", "נוח", "אווה", "איזבלה", "סופיה", "ג'קסון",
      "מיה", "לוקאס", "לוגאן", "איתן", "אמיליה", "הרפר", "אוולין", "אביגיל",
      "בנימין", "אלה", "סקרלט", "איידן", "גרייס", "קמילה", "לונה", "אריה",
      "קלואי", "אייזק", "זואי", "קיילב", "מילה", "הנרי"
   )

   return names.random()
}

fun rlast(): String {
   val lastNames = listOf(
      "סמית'", "ג'ונסון", "וויליאמס", "ג'ונס", "בראון", "דיוויס", "מילר", "וילסון",
      "מור", "טיילור", "אנדרסון", "תומס", "ג'קסון", "וויט", "האריס", "מרטין",
      "תומפסון", "גרסיה", "מרטינז", "רובינסון", "קלארק", "רודריגז", "לואיס", "לי",
      "ווקר", "הול", "אלן", "יאנג", "קינג", "רייט"
   )

   return lastNames.random()
}

fun generateIdNumbers(count: Int): List<Long> {
   val idNumbers = mutableListOf<Long>()

   repeat(count) {
      val randomId = (100_000_000L .. 999_999_999L).random()
      idNumbers.add(randomId)
   }

   return idNumbers
}

val students = mutableListOf<Student>().apply {
   for (id in generateIdNumbers(300)) {
      add(Student(id.toString(), ffirst(), rlast(), mutableMapOf()))
   }
}.toList()
val classes = mutableListOf<Class>().apply {
   val sCopy = students.toMutableList()
   for (id in 0 .. 30) {
      val v = sCopy.subList(0, 9)
      add(Class(id.toString(), "כיתה - $id", v.toMutableSet()))
      repeat(9) { sCopy.removeFirst() }
   }
}.toList()
val studentClass = mutableMapOf<Student, Class>()

val studentGrade = mutableMapOf<Student, Grade>()
val grades = mutableListOf<Grade>().apply {
   val sCopy = classes.toMutableList()
   for (id in 0 .. 30) {
      if (sCopy.size < 2) break
      val v = sCopy.subList(0, 2)
      val grade = Grade(id.toString(), "שכבה - $id", v.toMutableSet())
      add(grade)
      v.forEach { clazz ->
         clazz.students.forEach {
            studentClass[it] = clazz
            studentGrade[it] = grade
         }
      }
      repeat(2) { sCopy.removeFirst() }
   }
}.toList()