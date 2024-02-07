package lol.ezra.login.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ResultPanel() {
   MaterialTheme {
      Row {
         Text("Some result", modifier = Modifier.weight(0.8f))
         IconButton(
            {

            },
            modifier = Modifier.weight(0.2f).background(Color.Blue)
         ) {

         }

      }
   }
}