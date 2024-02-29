package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.Class
import lol.vfs.extensions.doneTests
import lol.vfs.extensions.approved

@Composable
fun ClassPanel(clazz: Class, onClick: (clazz: Class, selected: Boolean) -> Unit) {
   StatusPanel(clazz.prettyPrint,clazz.doneTests(), clazz.approved()) {
      onClick(clazz, it)
   }
}