package lol.vfs.pages.components.panel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.db.Class
import lol.vfs.db.StudyData
import lol.vfs.extensions.doneTests
import lol.vfs.extensions.approved
import lol.vfs.extensions.h
import lol.vfs.extensions.i
import lol.vfs.minilib.pq

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
         data.i()
      }
      4.h()
      Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
         Text(data.topic, fontSize = 30.sp)
         Text(data.shortDescription, fontSize = 15.sp, fontWeight = FontWeight.Bold)
         Text(
            data.description,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
            style = TextStyle(textDirection = TextDirection.Content),
            modifier = Modifier.padding(horizontal = 4.dp)
         )
      }
      Column(
         Modifier.weight(2f),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Bottom
      ) {
         OutlinedButton(
            onClick = {
               onClick(data)
            },
            border = BorderStroke(1.dp, Color.Magenta),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.outlinedButtonColors(
               contentColor = Color.Magenta,
            )
         ) {
            Text(text = "לקריאה מלאה")
         }
      }
   }
}