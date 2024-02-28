package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.Class
import lol.vfs.extensions.status

@Composable
fun ClassPanel(clazz: Class, onClick: (clazz: Class, selected: Boolean) -> Unit) {
   StatusPanel(clazz.prettyPrint, clazz.status(), clazz.status()) {
      onClick(clazz, it)
   }
}