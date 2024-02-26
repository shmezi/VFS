package lol.vfs.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import lol.vfs.requests.UserRequest
import lol.vfs.Database
import lol.vfs.LoginSession
import org.intellij.lang.annotations.Language


private const val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz"
fun randomString(length: Int) = (0 .. length).map { chars.random() }.joinToString("")

fun js(@Language("javascript") js: String) = js

//private val props = Properties().apply {
//   load(FileInputStream("settings.properties"))
//}
//
//fun setting(setting: String) = props.getProperty(setting)
suspend fun ApplicationCall.user(): UserRequest? {
   val session = principal<LoginSession>() ?: return null
   return Database.getUser(session.username, session.token)?.public()
}

val test = ""
fun <T> List<T>.encode() = this.joinToString()
fun String.decodeList() = this.split(", ")

fun main() {
   val s = listOf("Hi", "there", "shmezi").encode().decodeList()
   println(s)
}

suspend fun UserRequest.teacher() = Database.getTeacher(id)
suspend fun UserRequest.parent() = Database.getParent(id)
suspend fun UserRequest.doctor(): Nothing = TODO()