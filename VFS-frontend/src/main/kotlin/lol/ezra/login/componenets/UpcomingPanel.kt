package lol.ezra.login.componenets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UpcomingPanel() {
   MaterialTheme {
      Row {
         Text("Some type of a vaccine", modifier = Modifier.weight(0.8f))
         IconButton(
            {

            },
            modifier = Modifier.weight(0.2f)
         ) {

         }
      }
   }
}