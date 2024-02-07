package lol.ezra.user

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lol.ezra.utils.parent
import lol.ezra.utils.user

fun Application.setupRouting() {
   routing {
      authenticate("auth") {

         get("user") {

            call.respond(call.user() ?: return@get)
         }
         route("p") {
            get {
               val parent = call.user()?.parent() ?: return@get

            }

         }


      }
   }
}