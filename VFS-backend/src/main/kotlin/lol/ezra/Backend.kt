package lol.ezra

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.runBlocking
import lol.ezra.login.Database.createUser
import lol.ezra.login.setupSecurity
import lol.ezra.user.setupRouting


fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
   install(ContentNegotiation) {
      json()
   }
   setupSecurity()
   setupRouting()
}
