package lol.vfs.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lol.vfs.data.Database
import lol.vfs.data.School
import lol.vfs.data.School.assignCurrentYear
import lol.vfs.data.School.getGrade
import lol.vfs.data.UserAuth.register
import lol.vfs.lib.printing.pq
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.Student
import lol.vfs.requests.RegisterRequest


fun Application.classroom() {
   routing {
      "https://ezra.lol/studydata".pq()
      get("studyData") {
         call.respond(School.studyData())
      }

      get("student") {
         //TODO: Add authentication to user type
         val id = call.receive<String>()
         call.respond(School.getStudent(id) ?: throw NotFoundException("Student not found with id of $id"))
      }
      get("grade") {
         //TODO: Add authentication to user type
         val id = call.receive<Int>()

         call.respond(getGrade(id) ?: throw NotFoundException("Grade not found with id of $id"))
      }
      get("parent") {
         val id = call.receive<String>()
         call.respond(School.getParent(id) ?: throw NotFoundException("Parent not found with id of $id"))
      }
      get("doctor") {
         val id = call.receive<String>()
         call.respond(School.getDoctor(id) ?: throw NotFoundException("Doctor not found with id of $id"))
      }
      get("admin") {
         val id = call.receive<String>()
         call.respond(School.getAdmin(id) ?: throw NotFoundException("Admin  not found with id of $id"))
      }
      route("import"){
         post("classroom") {
            val f = call.receive<Pair<Map<Int, Grade>, List<Student>>>()
            Database.studentDB.insertMany(*f.second.toTypedArray())
            Database.gradeDB.insertMany(*f.first.values.toTypedArray())
            for (g in f.first.values)
               assignCurrentYear(g)

         }
         post("users") {
            val f = call.receive<List<Pair<RegisterRequest, List<String>>>>()
            for (v in f ){
               v.first.register(v.second)

            }

         }
      }
      route("update") {
         post("student") {
            val student = call.receive<Student>()
            call.respond(if (School.updateStudent(student)) HttpStatusCode.OK else HttpStatusCode.Forbidden)
         }
      }


   }
}