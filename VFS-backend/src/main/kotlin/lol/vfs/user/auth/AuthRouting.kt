package lol.vfs.user.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import lol.vfs.struct.Database
import lol.vfs.requests.LoginRequest
import lol.vfs.requests.RegisterRequest

fun Application.authRouting() {
   routing {
      route("auth") {

         post("login") {
            val loginRequest = call.receive<LoginRequest>()

            val token = Database.newToken(loginRequest.username, loginRequest.password)
            if (token == null) {
               call.respond(HttpStatusCode.Forbidden)
               return@post
            }

            call.sessions.set("login-session", LoginSession(loginRequest.username, token))
            call.respond(HttpStatusCode.OK)
         }


         post("logout") {
            Database.logout(call.principal<LoginSession>())
         }

         post("register") {
            val user = call.receive<RegisterRequest>()
            if (Database.userExist(user.id)) return@post
            Database.createUser(
               user.id,
               user.name,
               user.lastName,
               user.image,
               user.password,
               user.type
            )

         }

      }
   }
}