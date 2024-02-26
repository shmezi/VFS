package lol.ezra.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lol.ezra.utils.parent
import lol.ezra.utils.pq
import lol.ezra.utils.user

fun Application.setupRouting() {
   routing {
      authenticate("auth") {

         get("user") {
            val user = call.user().pq("User")
            if (user == null) {
               pq("ITS NULL!!!")
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