package lol.vfs.pages.components.tile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.Status
import lol.vfs.extensions.w
import lol.vfs.styling

@Composable
fun StatusTile(
   text: String,
   test: Status,
   approved: Status,
   selected: Boolean = false,
   selfControl: Boolean = true,
   showStatus: Boolean = true,
   onClick: (selected: Boolean) -> Unit
) {
   var selection by remember { mutableStateOf(selected) }
   Row(Modifier.padding(2.dp).background(
         if (if (selfControl) selection else selected) Color.Red else ColorPallet.BG_B.color
      ).fillMaxWidth().height(40.dp).clickable {

         selection = !selection
         onClick(selection)

      }

      .border(BorderStroke(1.dp, Color.Black)),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center

   ) {
      if (showStatus) {
         Row {
            test.image()
            3.w()
            approved.image()
         }
         10.w()
      }
      Text(style = styling, overflow = TextOverflow.Ellipsis,text=text)

   }
}