package lol.vfs.pages.components.panel.parent

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.model.StudyData
import lol.vfs.extensions.h
import lol.vfs.extensions.studyImage
import lol.vfs.pages.components.layout.DialogPupUp
import lol.vfs.styling

@Composable
fun LearningPanel(data: StudyData, onClick: (StudyData) -> Unit) {

   Column(
      Modifier
         .width(250.dp)
         .fillMaxHeight()
         .padding(5.dp)
         .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
         .clip(shape = RoundedCornerShape(10.dp))
         .background(Color.White),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top
   ) {
      Column(Modifier.weight(3f)) {
         data.studyImage()
      }
      4.h()
      Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
         Text(style = styling, overflow = TextOverflow.Ellipsis, text = data.topic, fontSize = 30.sp)
         Text(
            style = styling,
            overflow = TextOverflow.Ellipsis,
            text = data.shortDescription,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
         )
         Text(
            text = data.description, style = styling,
            modifier = Modifier.padding(horizontal = 4.dp)
         )
      }
      Column(
         Modifier.weight(1f),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Bottom
      ) {

         var dialog by remember { mutableStateOf(false) }
         if (dialog)
            AlertDialog(
               title = {
                  Text(
                     style = styling.copy(fontSize = 35.sp),
                     overflow = TextOverflow.Ellipsis,
                     text = data.topic,
                     textAlign = TextAlign.Center,
                     modifier = Modifier.fillMaxWidth()
                  )
               },
               text = {

                  Text(
                     style = styling,
                     overflow = TextOverflow.Ellipsis,
                     text = data.description,
                     modifier = Modifier.verticalScroll(rememberScrollState())
                  )


               },
               onDismissRequest = {
                  dialog = false
               },
               confirmButton = {
//                  dialog = false
               })
         OutlinedButton(
            onClick = {
               dialog = true
            },
            border = BorderStroke(1.dp, Color.Magenta),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.outlinedButtonColors(
               contentColor = Color.Magenta,
            )
         ) {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "לקריאה מלאה")
         }
      }
   }
}