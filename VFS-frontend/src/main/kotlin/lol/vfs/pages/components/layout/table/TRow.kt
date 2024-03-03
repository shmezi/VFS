package lol.vfs.pages.components.layout.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class TRow(private vararg val cells: @Composable ColumnScope.() -> Unit) {

   constructor(
      vararg cells: String?
   ) : this(
      *mutableListOf<@Composable ColumnScope.() -> Unit>().apply {
         cells.forEach {
            add {
               Text(it ?: "")
            }
         }
      }.toTypedArray()
   )


   @Composable
   fun build(rModifier: Modifier = Modifier, cModifier: Modifier = Modifier) {


      Row(
         rModifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly
      ) {
         for (cell in cells) {
            Column(
               cModifier.border(BorderStroke(1.dp, Color.Black)).weight(1f).fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ) { cell() }
         }
      }
   }


}