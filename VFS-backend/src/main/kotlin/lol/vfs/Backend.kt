package lol.vfs

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import lol.vfs.user.setupRouting


fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
   install(ContentNegotiation) {
      json()
   }
   authRouting()
   authSetup()
   setupRouting()
}
