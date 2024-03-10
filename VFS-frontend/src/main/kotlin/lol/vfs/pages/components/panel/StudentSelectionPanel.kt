package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import lol.vfs.model.users.Student
import lol.vfs.pages.components.tile.StudentTile
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade

@Composable
fun StudentSelectionPanel(
   student: MutableState<Student?>,
   students: SnapshotStateList<Student>,
   showStatus: Boolean = true
) {
   var student by student
   students.forEach {
      StudentTile(
         studentGrade[it]!!, studentClass[it]!!, it, student, showStatus = showStatus
      ) { a ->
         student = a
      }
   }

}