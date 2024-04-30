package lol.vfs.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import lol.vfs.data.Database
import lol.vfs.data.UserAuth.attemptLogin
import lol.vfs.data.UserAuth.authenticated
import lol.vfs.data.UserAuth.destroy
import lol.vfs.data.UserAuth.register
import lol.vfs.requests.LoginRequest
import lol.vfs.requests.RegisterRequest
import lol.vfs.requests.UserRequest

fun Application.auth() {
   install(Sessions) {
      cookie<UserSession>("login-session")
   }
   install(Authentication) {
      session<UserSession>("auth") {
         validate { session ->
            if (session.authenticated())
               session
            else null
         }
         challenge {
            call.respondRedirect("/login")
         }

      }

   }
   routing {

      route("auth") {
         post("login") {
            val loginRequest = call.receive<LoginRequest>()//Receive user's entered data
            val token = loginRequest.attemptLogin()
            val user = Database.userDB.get(loginRequest.id)
            if (token == null || user == null) { //If user is null or token is null -> No login :(
               call.respond(HttpStatusCode.Forbidden)
               return@post
            }
            call.sessions.set("login-session", UserSession(loginRequest.id, token)) //Set token
            call.respond(HttpStatusCode.OK, user.public()) //Changed to public method
         }

         post("logout") {
            call.receive<UserSession>().destroy()
         }
         post("register") {
            call.receive<RegisterRequest>().register()
         }

      }

   }
}