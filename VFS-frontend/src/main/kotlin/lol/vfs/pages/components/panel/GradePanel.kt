package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import lol.vfs.db.Grade
import lol.vfs.extensions.approved
import lol.vfs.extensions.doneTests

@Composable
fun GradePanel(grade: Grade, onClick: (grade: Grade, selected: Boolean) -> Unit) {
   StatusPanel(grade.prettyPrint, grade.doneTests(), grade.approved()) {
      onClick(grade, it)
   }
}