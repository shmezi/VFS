package lol.vfs.pages.components.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.runBlocking
import lol.vfs.LocalCache.getGradeIds
import lol.vfs.LocalCache.getGrades
import lol.vfs.LocalCache.getStudents
import lol.vfs.model.organizational.Class
import lol.vfs.model.users.Student
import lol.vfs.pages.components.tile.GradeTile

@Composable
fun GradeSelectionPanel(
   classes: SnapshotStateList<Class>,
   student: MutableState<Student?>,
   students: SnapshotStateList<Student>,
   showStatus: Boolean = true
) {
   var student by student

   getGrades(runBlocking { getGradeIds() }).forEach { grade ->
      GradeTile(grade, showStatus) { selected ->
         if (selected)
            classes.addAll(grade.classes.values)
         else {
            grade.classes.forEach { clazz ->
               val s = getStudents(clazz.value.students)
               if (s.firstOrNull { it == student } != null)
                  student = null
               students.removeAll(s)
            }
            classes.removeAll(grade.classes.values)

         }


      }
   }
}