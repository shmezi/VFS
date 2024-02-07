package lol.ezra.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import lol.ezra.UserType
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
               call.respond(token)
            }
         }

         post("logout") {
            Database.logout(call.principal<LoginSession>())
         }

         route("register") {

            post {
               val params = context.request.queryParameters

               val username = params["username"] ?: return@post
               val name = params["name"] ?: return@post
               val image = params["image"] ?: return@post
               val roles = params["roles"] ?: return@post
               val last = params["last"] ?: return@post

               val password = params["password"] ?: return@post
               if (Database.userExist(username)) return@post

               Database.createUser(
                  username,
                  name,
                  last,
                  image,
                  password,
                  *roles.decodeList().map { UserType.valueOf(it) }.toTypedArray()
               )

            }
         }

      }
   }
}