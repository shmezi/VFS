package lol.vfs.pages.components.tile

import androidx.compose.runtime.Composable
import lol.vfs.model.organizational.Grade
import lol.vfs.extensions.treatmentStatus
import lol.vfs.extensions.testStatus

@Composable
fun GradeTile(grade: Grade, showStatus: Boolean = true, onClick: (selected: Boolean) -> Unit) {
   StatusTile(grade.prettyPrint(), grade.testStatus(), grade.treatmentStatus(), showStatus = showStatus) {
      onClick(it)
   }
}