package lol.vfs.pages.components.panel

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import lol.vfs.db.organizational.Class
import lol.vfs.db.users.Student
import lol.vfs.extensions.DColumn

@Composable
fun RowScope.SelectionPanel(
   selected: MutableState<Student?>,
   gWeight: Float = 1f,
   cWeight: Float = 1f,
   sWeight: Float = 1.5f,
   showStatus: Boolean = true
) {
   val classes = remember { mutableStateListOf<Class>() }
   val students = remember { mutableStateListOf<Student>() }
   //Grade panels
   DColumn(gWeight) { GradeSelectionPanel(classes, selected, students, showStatus = showStatus) }
   //Class panels
   DColumn(cWeight) { ClassSelectionPanel(classes, selected, students, showStatus = showStatus) }
   //Student panels
   DColumn(sWeight) { StudentSelectionPanel(selected, students, showStatus = showStatus) }
}