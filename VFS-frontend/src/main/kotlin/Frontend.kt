import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import lol.vfs.client
import lol.vfs.model.StudyData
import lol.vfs.pages.auth.Login


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {
      window.name = "VFS"
      Navigator(Login)
   }

}

