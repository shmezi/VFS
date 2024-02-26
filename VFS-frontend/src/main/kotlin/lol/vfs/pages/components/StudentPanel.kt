package lol.vfs.pages.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lol.vfs.db.Student

@Composable
fun StudentPanel(student: Student, st: Student?, onClick: (Student) -> Unit) {
   val selected = st == student
   Text(
      "${student.id} ${student.name} ${student.lastName}",
      Modifier.border(BorderStroke(2.dp, Color.Black)).clickable {
         onClick(student)
      }
         .background(if (selected) Color.Red else Color.Blue)
   )
}