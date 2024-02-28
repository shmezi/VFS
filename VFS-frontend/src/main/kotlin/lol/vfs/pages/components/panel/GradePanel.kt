package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.Grade
import lol.vfs.extensions.status

@Composable
fun GradePanel(grade: Grade, onClick: (grade: Grade, selected: Boolean) -> Unit) {
   StatusPanel(grade.prettyPrint, grade.status(), grade.status()) {
      onClick(grade, it)
   }
}