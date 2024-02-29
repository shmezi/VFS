package lol.vfs.pages.components.panel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.assets.Status
import lol.vfs.assets.Status.Companion.status
import lol.vfs.db.Student
import lol.vfs.extensions.status
import javax.swing.text.html.ImageView

@Composable
fun TableRow(modifier: Modifier = Modifier, vararg contents: @Composable ColumnScope.() -> Unit) {
   Row(modifier) {
      contents.forEach {
         Column(
            modifier = Modifier.weight(1f).height(40.dp).border(BorderStroke(2.dp, Color.Black)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
         ) { it(this) }
      }
   }
}

@Composable
fun TablePanel(student: Student?, state: Boolean) {
   student ?: return

   val mapping: SnapshotStateMap<String, Status> =
      if (!state) mutableStateMapOf(*student.medicalTests.map { Pair(it.key, it.value.status()) }.toTypedArray())
      else mutableStateMapOf(*student.medicalTreatments.map { Pair(it.key, it.value.status()) }.toTypedArray())

   Row {
      Column(verticalArrangement = Arrangement.Center, modifier = Modifier.verticalScroll(rememberScrollState())) {
         TableRow(Modifier.bg(ColorPallet.BACKGROUNDP), { Text("סטטוס") }, { Text("שם טיפול") })
         mapping.forEach {
            TableRow(Modifier.height(50.dp), { it.value.i() }, { Text(it.key) })
         }
      }

   }
}

