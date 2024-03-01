package lol.vfs.pages.components.panel.parent

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import lol.vfs.extensions.w

@Composable
fun ParentReminderMessage(approved: Boolean, click: () -> Unit) {
   var text by remember { mutableStateOf("עוד לא אישרת את מילוי הצהרת הבריאות!") }
   var color by remember { mutableStateOf(Color.Red) }
   if (approved) {
      text = "תודה שאישרתם מילוי ההצהרה!"
      color = Color.Green
   } else {
      text = "עוד לא אישרת את מילוי הצהרת הבריאות!"
      color = Color.Red
   }
   4.w()
   Column(horizontalAlignment = Alignment.End) {
      Text(text, color = color, textAlign = TextAlign.Right, fontSize = 25.sp)
      if (!approved)
         Button({
            click()
         }) { Text("לאישור") }

   }
}