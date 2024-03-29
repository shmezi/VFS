package lol.vfs.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lol.vfs.data.School
import lol.vfs.model.users.Student


fun Application.classroom() {
   routing {
      get("studyData") {
         call.respond(School.studyData())
      }
      get("student") {
         //TODO: Add authentication to user type
         val id = call.receive<String>()
         call.respond(School.getStudent(id) ?: return@get)
      }
      get("grade") {
         //TODO: Add authentication to user type

         val id = call.receive<String>()

         call.respond(School.getGrade(id) ?: return@get)
      }
      get("parent") {
         val id = call.receive<String>()
         call.respond(School.getParent(id) ?: return@get)
      }
      get("doctor") {
         val id = call.receive<String>()
         call.respond(School.getDoctor(id) ?: return@get)
      }
      get("admin") {
         val id = call.receive<String>()
         call.respond(School.getAdmin(id) ?: return@get)
      }

      route("update") {
         post("student") {
            val student = call.receive<Student>()
            call.respond(if (School.updateStudent(student)) HttpStatusCode.OK else HttpStatusCode.Forbidden)
         }
      }


   }
}