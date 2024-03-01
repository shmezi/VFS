package lol.vfs.pages.components.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class Row(private vararg val cells: @Composable ColumnScope.() -> Unit) {
   @Composable
   fun build(rModifier: Modifier = Modifier, cModifier: Modifier = Modifier) {

      val cells = remember { mutableStateListOf(*cells) }
      Row(
         rModifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly
      ) {
         for (cell in cells) {
            Column(
               cModifier.border(BorderStroke(2.dp, Color.Black)).weight(1f),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ) { cell() }
         }
      }
   }


}