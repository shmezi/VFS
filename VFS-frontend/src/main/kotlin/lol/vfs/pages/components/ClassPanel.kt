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
import lol.vfs.db.Class

@Composable
fun ClassPanel(clazz: Class, onClick: (Class, MutableState<Boolean>) -> Unit) {
   val selectState = remember { mutableStateOf(false) }
   var selected by selectState
   Text(
      "${clazz.id} has ${clazz.students.size} students",
      Modifier.border(BorderStroke(2.dp, Color.Black)).clickable {
         selected = !selected
         onClick(clazz, selectState)
      }
         .background(if (selected) Color.Red else Color.Blue)
   )
}