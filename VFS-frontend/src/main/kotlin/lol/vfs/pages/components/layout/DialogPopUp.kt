package lol.vfs.pages.components.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogPupUp(content: @Composable ColumnScope.() -> Unit) {
   var dialog by remember { mutableStateOf(true) }
   if (dialog) {
      Column(Modifier.fillMaxSize().background(color = Color.Blue.copy(0.8f))) {
         Dialog(onDismissRequest = {
            dialog = false
         }, content = {
            Column(
               Modifier.fillMaxHeight(0.6f).fillMaxWidth(1f).clip(RoundedCornerShape(7))
                  .border(border = BorderStroke(2.dp, Color.Black)).background(Color.White),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               content()
            }

         })
      }
   }
}
