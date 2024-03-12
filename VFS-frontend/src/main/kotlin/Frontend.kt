import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import lol.vfs.pages.auth.Login
import lol.vfs.pages.auth.Register
import lol.vfs.pages.user.Admin
import lol.vfs.pages.user.User
import lol.vfs.pages.user.doctor.Doctor
import lol.vfs.pages.user.parent.Parent


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {
      window.name = "VFS"
      Navigator(Admin)
   }
}

