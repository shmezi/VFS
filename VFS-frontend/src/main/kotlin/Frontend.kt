import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import lol.vfs.pages.user.Admin
import lol.vfs.pages.user.doctor.Doctor
import lol.vfs.pages.user.parent.Parent
import javax.print.Doc


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {
      Navigator(Doctor)
   }
}

