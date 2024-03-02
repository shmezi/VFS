package lol.vfs

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import lol.vfs.db.organizational.Class
import lol.vfs.db.users.UserType
import lol.vfs.requests.UserRequest

val client = HttpClient(CIO) {
   install(HttpCookies)
   install(ContentNegotiation) {
      json()
   }
}

const val urlStem = "http://localhost:8080"
fun String.url() = "$urlStem/$this"

suspend fun getUser(): UserRequest = UserRequest("337616346", "עזרא", "גולומבק", UserType.DOCTOR)
//{
//   val user = client.get("user".url()).body<UserRequest>()
//   return user
//}


fun getClasses(): Set<Class> {
   return runBlocking { client.get("classes".url()).body() }
}