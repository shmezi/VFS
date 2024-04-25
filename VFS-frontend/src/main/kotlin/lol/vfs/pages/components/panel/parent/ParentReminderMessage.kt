package lol.vfs.pages.components.panel.parent

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import lol.vfs.assets.Status
import lol.vfs.extensions.treatmentStatus
import lol.vfs.extensions.w
import lol.vfs.model.users.Student
import lol.vfs.pages.user.parent.ApprovalDialog
import lol.vfs.styling

@Composable
fun ParentReminderMessage(kid: Student) {
   var text by remember { mutableStateOf("עוד לא אישרת את מילוי הצהרת הבריאות!") }
   var color by remember { mutableStateOf(Color.Red) }
   val approved = kid.treatmentStatus() != Status.DENIED
   if (approved) {
      text = "תודה שאישרתם מילוי ההצהרה!"
      color = Color.Green
   } else {
      text = "עוד לא אישרת את מילוי הצהרת הבריאות!"
      color = Color.Red
   }
   4.w()
   Column(horizontalAlignment = Alignment.End) {
      Text(
         style = styling,
         overflow = TextOverflow.Ellipsis,
         text = text,
         color = color,
         textAlign = TextAlign.Right,
         fontSize = 25.sp
      )
      var dialog by mutableStateOf(false)
      if (dialog) kid.ApprovalDialog {
         dialog = false
      }
      if (!approved)
         Button({
            dialog = true
         }) { Text(style = styling, overflow = TextOverflow.Ellipsis, text = "לאישור") }

   }
}