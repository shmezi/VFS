package lol.vfs.pages.components.table

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TTable(
   header: Row,
   gModifier: Modifier = Modifier,
   hModifier: Modifier = Modifier.height(30.dp),
   rModifier: Modifier = Modifier.height(20.dp),
   vararg rows: Row
) {
   val rows = remember { mutableStateListOf(*rows) }
   Column(gModifier.background(Color.Gray).border(BorderStroke(2.dp, Color.Black))) {
      header.build(cModifier = hModifier)
      Column (modifier = Modifier.verticalScroll(rememberScrollState())){
         rows.forEach {
         it.build(cModifier = rModifier)
      }
      }
   }
}