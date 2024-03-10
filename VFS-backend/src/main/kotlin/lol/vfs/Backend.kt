package lol.vfs


import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.runBlocking
import lol.vfs.struct.Database
import lol.vfs.user.auth.authRouting
import lol.vfs.user.auth.authSetup
import lol.vfs.user.setupRouting


fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
   install(ContentNegotiation) {
      json()
   }
   Database
   authRouting()
   authSetup()
   setupRouting()

   runBlocking {
//      Database.classDB.insertOne(Class("wows", "hello", mutableSetOf()))
   }
}

