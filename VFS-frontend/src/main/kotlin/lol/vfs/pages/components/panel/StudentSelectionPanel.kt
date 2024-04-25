package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import lol.vfs.LocalCache
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria
import lol.vfs.model.users.Student
import lol.vfs.pages.components.tile.StudentTile

@Composable
fun StudentSelectionPanel(
   selectedStudent: MutableState<Student?>, students: SnapshotStateList<Student>, showStatus: Boolean = true
) {
   var student by selectedStudent
   students.forEach {
      StudentTile(
         student = it,
         selected = selectedStudent.value,
         showStatus = showStatus
      ) { a ->
         student = a
      }
   }

}