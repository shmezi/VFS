package lol.vfs.struct

import lol.vfs.Database
import lol.vfs.db.organizational.Class
import lol.vfs.db.testing.Medical
import lol.vfs.db.testing.TestResult
import lol.vfs.db.users.Parent
import lol.vfs.db.users.Student
import lol.vfs.db.users.Teacher


suspend fun Class.getStudents() = students.map { Database.getStudent(it.id) }
suspend fun Class.assignTest(test: Medical) {
   getStudents().forEach { it?.tests?.set(test.name, TestResult()) }
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
suspend fun Student.getTests() = tests.map { Database.getTest(it.key) }

suspend fun Teacher.getClass(id: String) = Database.getClass(id)
suspend fun Teacher.getClasses() = classes.map { Database.getClass(it) }
