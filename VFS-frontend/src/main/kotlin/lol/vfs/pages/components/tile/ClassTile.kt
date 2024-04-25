package lol.vfs.pages.components.tile

import androidx.compose.runtime.Composable
import lol.vfs.model.organizational.Class
import lol.vfs.extensions.testStatus
import lol.vfs.extensions.treatmentStatus
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria

@Composable
fun ClassTile(clazz: Class, showStatus: Boolean = true, onClick: (selected: Boolean) -> Unit) {
   StatusTile(
      "${clazz.grade.toGimatria()} - ${clazz.id}",
      clazz.testStatus(),
      clazz.treatmentStatus(),
      showStatus = showStatus
   ) {
      onClick(it)
   }
}