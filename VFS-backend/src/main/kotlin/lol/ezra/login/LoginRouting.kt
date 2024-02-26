package lol.ezra.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.reflect.*
import lol.ezra.PublicUser
import lol.ezra.UserType
import lol.ezra.requests.Register
import lol.ezra.utils.decodeList

fun Application.loginRouting() {
   routing {
      route("auth") {
         route("login") {
            post {
               val params = context.request.queryParameters
               val username = params["username"] ?: return@post
               val password = params["password"] ?: return@post
               val token = Database.newToken(username, password)
               if (token == null) {
                  call.respond(HttpStatusCode.Forbidden)
                  return@post
               }
               call.sessions.set("login-session", LoginSession(username, token))
               call.respond(HttpStatusCode.OK, token)
            }
         }

         post("logout") {
            Database.logout(call.principal<LoginSession>())
         }

         route("register") {

            post {
               val user = call.receive<Register>()
               if (Database.userExist(user.id)) return@post
               Database.createUser(
                  user.id,
                  user.name,
                  user.lastName,
                  user.image,
                  user.password,
                  *user.type.toTypedArray()
               )

            }
         }

      }
   }
}