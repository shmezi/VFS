package lol.vfs.utils

import lol.vfs.db.Class
import lol.vfs.db.Student

/**
 * Sample data set
 */


fun ffirst(): String {
   val names = listOf(
      "Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella", "Sophia", "Jackson",
      "Mia", "Lucas", "Logan", "Ethan", "Amelia", "Harper", "Evelyn", "Abigail",
      "Benjamin", "Ella", "Scarlett", "Aiden", "Grace", "Camila", "Luna", "Aria",
      "Chloe", "Isaac", "Zoe", "Caleb", "Mila", "Henry"
   )

   return names.random()
}

fun rlast(): String {
   val lastNames = listOf(
      "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
      "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
      "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
      "Walker", "Hall", "Allen", "Young", "King", "Wright"
   )

   return lastNames.random()
}

val students = mutableListOf<Student>().apply {
   for (id in 0 .. 300) {
      add(Student(id.toString(), ffirst(), rlast(), mutableMapOf()))
   }
}.toList()
val classes = mutableListOf<Class>().apply {
   val sCopy = students.toMutableList()
   for (id in 0 .. 30) {
      val v = sCopy.subList(0, 9)
      add(Class(id.toString(), v.toMutableSet()))
      repeat(9) { sCopy.removeFirst() }
   }
}.toList()