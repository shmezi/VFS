package lol.vfs.requests

import lol.vfs.model.organizational.Class
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.Student
import lol.vfs.model.users.UserType
import java.io.File

fun importClassroomData(file: File): Pair<Map<Int, Grade>, List<Student>> {
   if (file.extension != "csv") throw NotImplementedError("File was not a csv!")
   var headerCheck = true
   val students = mutableListOf<Student>()
   val grades = mutableMapOf<Int, Grade>()
   file.forEachLine {
      if (!headerCheck) {
         val values = it.split(",")
         fun Int.v() = values[this]
         fun Int.vi() = values[this].toInt()
         //Decoded:
         val id = 0.v()
         val first = 1.v()
         val last = 2.v()
         val grade = 3.vi()
         val clazz = 4.vi()
         val student = Student(
            id,
            first,
            last,
            grade,
            clazz,
            mutableMapOf(),
            mutableMapOf()
         )
         students.add(student)
         val g = grades.getOrPut(grade) { Grade(grade, mutableMapOf()) }
         g.classes.getOrPut(clazz.toString()) { Class(clazz, g.id, mutableSetOf()) }.students.add(id)
      } else headerCheck = false
   }
   return Pair(grades, students)
}


fun importUsers(file: File): List<Pair<RegisterRequest, List<String>>> {
   if (file.extension != "csv") throw NotImplementedError("File was not a csv!")
   if (file.extension != "csv") throw NotImplementedError("File was not a csv!")
   var headerCheck = true
   val requests = mutableListOf<Pair<RegisterRequest, List<String>>>()
   file.forEachLine {
      if (!headerCheck) {
         val values = it.split(",")
         fun Int.v() = values[this]
         fun Int.vi() = values[this].toInt()

         val id = 0.v()
         val pwd = 1.v()
         val first = 2.v()
         val last = 3.v()
         val type = UserType.valueOf(4.v().uppercase())
         val ids = 5.v().split("|")
         requests.add(Pair(RegisterRequest(id, first, last, type, pwd), ids))

      } else headerCheck = false
   }
   return requests
}