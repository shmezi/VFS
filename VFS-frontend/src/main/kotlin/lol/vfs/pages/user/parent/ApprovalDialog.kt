package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking
import lol.vfs.LocalCache
import lol.vfs.model.users.Student
import lol.vfs.pages.components.button.LinkButton
import lol.vfs.pages.components.layout.DialogPupUp
import lol.vfs.styling

@Composable
fun Student?.ApprovalDialog(approveAll: () -> Unit) {
   val kid = this ?: return
   DialogPupUp {
      Text(
         style = styling, overflow = TextOverflow.Ellipsis, text =
         "האם מילאתם את הצהרת הבריאות באתר משרד החינוך?",
         fontSize = 30.sp,
         textAlign = TextAlign.Center
      )
      LinkButton("https://parents.education.gov.il/prhnet/contact-us/confirmations/annual-health-statement") {
         Text(style = styling, overflow = TextOverflow.Ellipsis, text = "לאישור באתר משרד החינוך")
      }
      Column(
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Bottom,
         modifier = Modifier.fillMaxHeight()
      ) {
         //Approve all button
         Button(
            {
               kid.approveAll()
               runBlocking { LocalCache.postToCloud(kid.id) }
               approveAll()
            },
         ) {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "כן, אישרתי הכל!")
         }
      }
   }

}