package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import lol.vfs.LocalCache
import lol.vfs.LocalCache.getStudents
import lol.vfs.model.organizational.Class
import lol.vfs.model.users.Student
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
         val s = getStudents(clazz.students)
         if (selected) students.addAll(s)
         else {
            students.removeAll(s)
            if (s.firstOrNull { it == student.value } != null)
               student.value = null
         }

      }
   }

}