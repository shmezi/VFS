import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import lol.ezra.Class
import lol.ezra.PublicUser
import lol.ezra.login.pages.auth.Login

val client = HttpClient(CIO) {
   install(HttpCookies)
   install(ContentNegotiation) {
      json()
   }


}
const val urlStem = "http://localhost:8080"
fun String.url() = "$urlStem/$this"

suspend fun getUser(): PublicUser {
   val user = client.get("user".url()).body<PublicUser>()
   return user
}

suspend fun getClasses(): Set<Class> {
   return client.get("classes".url()).body()
}

@Composable
fun Screen.nav() = LocalNavigator.currentOrThrow.push(this)
fun main() = application {

   Window(onCloseRequest = ::exitApplication) {

      Navigator(Login)
   }
}

