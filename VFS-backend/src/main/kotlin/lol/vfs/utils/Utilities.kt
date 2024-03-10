package lol.vfs.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import lol.vfs.requests.UserRequest
import lol.vfs.struct.Database
import lol.vfs.user.auth.LoginSession
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



suspend fun UserRequest.teacher() = Database.getTeacher(id)
suspend fun UserRequest.parent() = Database.getParent(id)
