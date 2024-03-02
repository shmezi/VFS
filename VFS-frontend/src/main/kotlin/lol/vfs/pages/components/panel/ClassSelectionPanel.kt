package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import lol.vfs.db.organizational.Class
import lol.vfs.db.users.Student
import lol.vfs.pages.components.tile.ClassTile

@Composable
fun ClassSelectionPanel(
   classes: SnapshotStateList<Class>,
   student: MutableState<Student?>,
   students: SnapshotStateList<Student>,
   showStatus: Boolean = true
) {
   classes.forEach { clazz ->
      ClassTile(clazz, showStatus = showStatus) { selected ->
         if (selected) students.addAll(clazz.students)
         else {
            students.removeAll(clazz.students)
            if (clazz.students.firstOrNull { it == student.value } != null)
               student.value = null
         }

      }
   }

}