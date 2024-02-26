package lol.vfs.struct

import lol.vfs.Database
import lol.vfs.db.*


suspend fun Class.getStudents() = students.map { Database.getStudent(it.id) }
suspend fun Class.assignTest(test: MedTest) {
   getStudents().forEach { it?.medicalTests?.set(test.name, null) }
}

suspend fun Parent.getKids(): Set<Student> {
   val students = mutableSetOf<Student>()
   for (student in kids) {
      val s = Database.getStudent(student) ?: continue
      students.add(s)
   }
   return students

}

suspend fun Student.getTest(id: String) = Database.getTest(id)
suspend fun Student.getTests() = medicalTests.map { Database.getTest(it.key) }

suspend fun Teacher.getClass(id: String) = Database.getClass(id)
suspend fun Teacher.getClasses() = classes.map { Database.getClass(it) }
