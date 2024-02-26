package lol.ezra.login.pages.teacher

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lol.ezra.Class
import lol.ezra.login.bg

@Composable
fun ClassPanel(clazz: Class, onClick: (Class, MutableState<Boolean>) -> Unit) {
   var selected = remember { mutableStateOf(false) }
   Text(
      "${clazz.id} has ${clazz.students.size} students",
      Modifier.border(BorderStroke(2.dp, Color.Black)).clickable {
         selected.value = !selected.value
         onClick(clazz, selected)
      }
         .background(if (selected.value) Color.Red else Color.Blue)
   )
}