package lol.vfs.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import lol.vfs.minilib.pq
import lol.vfs.struct.Database
import lol.vfs.utils.parent
import lol.vfs.utils.user

fun Application.setupRouting() {
   routing {
      post("cl") {

         call.respond(runBlocking { Database.getClass("wows") ?: throw NullPointerException() })
      }
      authenticate("auth") {
         get("user") {
            val user = call.user().pq("User")
            if (user == null) {
               call.respond(HttpStatusCode.MethodNotAllowed, "Not found!")
               return@get
            }
            call.respond(user)
         }
         route("p") {
            get {
               val parent = call.user()?.parent() ?: return@get

            }

         }


      }
   }
}