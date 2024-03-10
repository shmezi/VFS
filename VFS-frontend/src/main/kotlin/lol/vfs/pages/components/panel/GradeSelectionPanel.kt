package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import lol.vfs.model.organizational.Class
import lol.vfs.model.users.Student
import lol.vfs.pages.components.tile.GradeTile
import lol.vfs.utils.grades

@Composable
fun GradeSelectionPanel(
   classes: SnapshotStateList<Class>,
   student: MutableState<Student?>,
   students: SnapshotStateList<Student>,
   showStatus: Boolean = true
) {
   var student by student

   grades.forEach { g ->
      GradeTile(g, showStatus = showStatus) { grade, selected ->
         if (selected)
            classes.addAll(grade.classes)
         else {
            grade.classes.forEach { clazz ->
               if (clazz.students.firstOrNull { it == student } != null)
                  student = null
               students.removeAll(clazz.students)
            }
            classes.removeAll(grade.classes)

         }


      }
   }
}