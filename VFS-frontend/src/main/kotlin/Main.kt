import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import lol.ezra.login.pages.LoginPage
import lol.ezra.login.pages.ParrentPage
import lol.ezra.login.pages.TeacherPage
import lol.ezra.login.user.User

val client = HttpClient(CIO) {
   install(HttpCookies)
   install(ContentNegotiation) {
      json()
   }


}
const val urlStem = "http://localhost:8080"
fun String.url() = "$urlStem$/this"

suspend fun getUser(): User {
   return client.get("user".url()).body()
}

suspend fun getClasses(): Set<Class> {
   return client.get("classes".url()).body()
}

fun main() = application {
   Window(onCloseRequest = ::exitApplication) {

      Navigator(TeacherPage)

   }

}

