package lol.vfs.pages.components.layout.table

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lol.vfs.minilib.pq

@Composable
fun TTable(
   header: TRow,
   vararg rows: TRow,
   gModifier: Modifier = Modifier,
   hModifier: Modifier = Modifier.height(30.dp),
   rModifier: Modifier = Modifier.height(20.dp),
) {
   Column(gModifier.background(Color.Gray).border(BorderStroke(2.dp, Color.Black))) {
      header.build(hModifier)//TODO cModifier ?
      Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
         rows.forEach {
            it.build(rModifier) //TODO cModifier adding ?

         }
      }
   }
}