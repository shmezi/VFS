package lol.vfs.pages.components.tile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lol.vfs.LocalCache
import lol.vfs.model.users.Student


@Composable
fun StudentInfoPanel(student: Student?) {
   student ?: return
   Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End) {
      Column(Modifier, horizontalAlignment = Alignment.End) {
         Text(
            "${LocalCache.studentClazz[student.id]}/${LocalCache.studentGrade[student.id]}", fontSize = 30.sp
         )
      }
      Column(
         Modifier, horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Top
      ) {
         Text("${student.name} ${student.lastName}", fontSize = 45.sp, maxLines = 1, fontWeight = FontWeight.Bold)
         Text(student.id, fontSize = 30.sp, fontWeight = FontWeight.Bold)
      }
   }
}