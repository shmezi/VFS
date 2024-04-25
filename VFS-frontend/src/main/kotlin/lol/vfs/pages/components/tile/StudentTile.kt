package lol.vfs.pages.components.tile

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.assets.ColorPallet
import lol.vfs.model.users.Student
import lol.vfs.extensions.*
import lol.vfs.lib.gimatria.GimatriaConverter.toGimatria
import lol.vfs.lib.printing.pq
import lol.vfs.styling

@Composable
fun StudentTile(
   student: Student,
   selected: Student?,
   showStatus: Boolean = true,
   onClick: (student: Student) -> Unit
) {
   Row(
      Modifier
         .padding(2.dp)
         .background(if (selected == student) Color.Red else ColorPallet.BG_B.color)
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
         Text(
            style = styling,
            overflow = TextOverflow.Ellipsis,
            text = "${student.grade.toGimatria()}/${student.clazz}"
         )
         3.h()
         if (showStatus)
            Row {
               student.testStatus().image()
               3.w()
               student.treatmentStatus().image()
            }
      }
      Column(Modifier.weight(7f), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.End) {
         Text(
            style = styling,
            overflow = TextOverflow.Ellipsis,
            text = "${student.name} ${student.lastName}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
         )
         Text(style = styling, overflow = TextOverflow.Ellipsis, text = student.id)
      }

   }

}