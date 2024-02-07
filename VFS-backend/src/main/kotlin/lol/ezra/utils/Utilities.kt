package lol.ezra.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import lol.ezra.PublicUser
import lol.ezra.login.Database
import lol.ezra.login.LoginSession
import org.intellij.lang.annotations.Language
import java.io.FileInputStream
import java.util.*


private const val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz"
fun randomString(length: Int) = (0 .. length).map { chars.random() }.joinToString("")

fun js(@Language("javascript") js: String) = js

private val props = Properties().apply {
   load(FileInputStream("settings.properties"))
}

fun setting(setting: String) = props.getProperty(setting)
suspend fun ApplicationCall.user(): PublicUser? {
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

suspend fun PublicUser.teacher() = Database.getTeacher(id)
suspend fun PublicUser.parent() = Database.getParent(id)
suspend fun PublicUser.doctor(): Nothing = TODO()