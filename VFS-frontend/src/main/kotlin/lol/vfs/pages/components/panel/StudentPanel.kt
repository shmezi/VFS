package lol.vfs.pages.components.panel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import lol.vfs.assets.ColorPallet
import lol.vfs.db.Class
import lol.vfs.db.Grade
import lol.vfs.db.Student
import lol.vfs.extensions.h
import lol.vfs.extensions.sp
import lol.vfs.extensions.status
import lol.vfs.extensions.w

@Composable
fun StudentPanel(
   grade: Grade,
   clazz: Class,
   student: Student,
   selected: Student?,
   onClick: (student: Student) -> Unit
) {
   Row(
      Modifier
         .padding(2.dp)
         .background(if (selected == student) Color.Red else ColorPallet.BACKGROUNDS.c)
         .clickable {
            onClick(student)
         }
         .height(70.dp)
         .fillMaxWidth()
         .border(BorderStroke(1.dp, Color.Black)),
      verticalAlignment = Alignment.Top,
   ) {

      Column(
         Modifier.weight(3f).fillMaxHeight(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         Text("${clazz.id}/${grade.id}")
         3.h()
         Row {
            student.status().i()
            3.w()
            student.status().i()
         }
      }
      Column(Modifier.weight(7f), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.End) {
         Text("${student.name} ${student.lastName}", fontSize = 30.sp(), fontWeight = FontWeight.Bold)
         Text(student.id)
      }

   }

}