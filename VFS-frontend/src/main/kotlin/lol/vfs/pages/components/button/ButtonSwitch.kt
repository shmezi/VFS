package lol.vfs.pages.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import lol.vfs.extensions.w


@Composable
fun ButtonSwitch(onClick: (Boolean) -> Unit) {
   var state by remember { mutableStateOf(true) }
   Row {
      OutlinedButton(
         onClick = {
            state = false
            onClick(false)
         },

         border = BorderStroke(1.dp, Color.White),
         shape = RoundedCornerShape(50), // = 50% percent
         colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            backgroundColor = if (state) Color.Gray else Color.Green
         )
      ) {
         Text(text = "חיסונים")
      }
      5.w()
      OutlinedButton(
         onClick = {
            state = true
            onClick(true)
         },
         border = BorderStroke(1.dp, Color.White),
         shape = RoundedCornerShape(50), // = 50% percent
         colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            backgroundColor = if (state) Color.Green else Color.Gray
         )
      ) {
         Text(text = "בדיקות")
      }
   }
}