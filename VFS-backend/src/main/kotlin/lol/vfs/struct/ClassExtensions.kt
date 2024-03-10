package lol.vfs.struct

import lol.vfs.model.organizational.Class
import lol.vfs.model.testing.Medical
import lol.vfs.model.testing.TestResult
import lol.vfs.model.users.Parent
import lol.vfs.model.users.Student
import lol.vfs.model.users.Teacher


suspend fun Class.getStudents() = students.map { Database.getStudent(it.id) }
suspend fun Class.assignTest(test: Medical) {
   getStudents().forEach { it?.tests?.set(test.name, TestResult()) }
}

suspend fun Parent.getKids(): Set<Student> {
   val students = mutableSetOf<Student>()
   for (student in kids) {
      val s = Database.getStudent(student.id) ?: continue
      students.add(s)
   }
   return students

}

suspend fun Student.getTest(id: String) = Database.getTest(id)
suspend fun Student.getTests() = tests.map { Database.getTest(it.key) }

suspend fun Teacher.getClass(id: String) = Database.getClass(id)
suspend fun Teacher.getClasses() = classes.map { Database.getClass(it.id) }
