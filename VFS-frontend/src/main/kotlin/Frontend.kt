import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import lol.vfs.pages.auth.Login
import lol.vfs.pages.user.Doctor


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {

      Navigator(Doctor)
   }
}

