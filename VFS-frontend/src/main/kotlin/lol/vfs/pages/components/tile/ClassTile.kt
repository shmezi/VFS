package lol.vfs.pages.components.tile

import androidx.compose.runtime.Composable
import lol.vfs.extensions.testStatus
import lol.vfs.extensions.treatmentStatus
import lol.vfs.model.organizational.Age.Companion.toAge
import lol.vfs.model.organizational.Class

@Composable
fun ClassTile(clazz: Class, showStatus: Boolean = true, onClick: (selected: Boolean) -> Unit) {
   StatusTile(
      "${clazz.grade.toAge().gradePrettyPrint()} - ${clazz.id}",
      clazz.testStatus(),
      clazz.treatmentStatus(),
      showStatus = showStatus
   ) {
      onClick(it)
   }
}