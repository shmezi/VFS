import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import lol.vfs.pages.auth.Login


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {
      window.name = "VFS"
      Navigator(Login)
   }

}

