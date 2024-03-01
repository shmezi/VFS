package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.organizational.Class
import lol.vfs.extensions.testStatus
import lol.vfs.extensions.treatmentStatus

@Composable
fun ClassPanel(clazz: Class, showStatus: Boolean = true, onClick: (clazz: Class, selected: Boolean) -> Unit) {
   StatusPanel(clazz.prettyPrint, clazz.testStatus(), clazz.treatmentStatus(), showStatus = showStatus) {
      onClick(clazz, it)
   }
}