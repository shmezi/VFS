package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.organizational.Grade
import lol.vfs.extensions.treatmentStatus
import lol.vfs.extensions.testStatus

@Composable
fun GradePanel(grade: Grade, showStatus: Boolean = true, onClick: (grade: Grade, selected: Boolean) -> Unit) {
   StatusPanel(grade.prettyPrint, grade.testStatus(), grade.treatmentStatus(), showStatus = showStatus) {
      onClick(grade, it)
   }
}