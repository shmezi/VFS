package lol.vfs.pages.components.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lol.vfs.db.Student
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade

@Composable
fun StudentInfoPanel(student: Student?) {
   student ?: return
   Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End) {
      Column(Modifier, horizontalAlignment = Alignment.End) {
         Text(
            "${studentClass[student]?.id}/${studentGrade[student]?.id}",
            fontSize = 30.sp
         )
      }
      Column(
         Modifier,
         horizontalAlignment = Alignment.End,
         verticalArrangement = Arrangement.Top
      ) {
         Text("${student.name} ${student.lastName}", fontSize = 45.sp, maxLines = 1, fontWeight = FontWeight.Bold)
         Text(student.id, fontSize = 30.sp, fontWeight = FontWeight.Bold)
      }
   }
}