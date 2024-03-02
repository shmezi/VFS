import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import lol.vfs.pages.user.User
import lol.vfs.pages.user.parent.Parent


fun main() = application {

   Window(onCloseRequest = ::exitApplication) {
      Navigator(Parent)
   }
}

