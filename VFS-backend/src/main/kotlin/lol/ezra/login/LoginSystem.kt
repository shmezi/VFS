package lol.ezra.login

import io.ktor.server.application.*

fun Application.setupSecurity() {
  loginAuth()
  loginRouting()
  loginSessionSetup()
}