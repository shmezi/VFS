package lol.ezra

import io.ktor.server.application.*
import io.ktor.server.cio.*
import kotlinx.coroutines.runBlocking
import lol.ezra.login.setupSecurity
import lol.ezra.user.setupRouting


fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
   setupSecurity()
   setupRouting()
   runBlocking {
//        UserDatabase.createUser("shmezi", "123456789", UserType.ADMIN)

   }
}
