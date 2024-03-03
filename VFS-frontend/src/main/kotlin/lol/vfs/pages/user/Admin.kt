package lol.vfs.pages.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.assets.Status
import lol.vfs.db.users.Student
import lol.vfs.extensions.status
import lol.vfs.extensions.w
import lol.vfs.getUser
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.panel.SelectionPanel
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.pages.components.tile.StudentInfoPanel


object Admin : Screen {

   @Composable
   override fun Content() {
      val studentState = remember { mutableStateOf<Student?>(null) }
      val student by studentState
      PageLayout(runBlocking { getUser() }) {
         Row(
            Modifier.fillMaxSize().bg(ColorPallet.BACKGROUNDP)
         ) {

            SelectionPanel(studentState)

            //Student info panel
            Column(Modifier.weight(2f).fillMaxHeight(), horizontalAlignment = Alignment.End) {
               val student = student ?: return@Column
               StudentInfoPanel(student)
               var state by remember { mutableStateOf(false) }

               Row {
                  ButtonSwitch {
                     state = it
                  }
                  5.w()
               }
               val mapping: SnapshotStateMap<String, Status> =
                  if (!state) mutableStateMapOf(*student.tests.map { Pair(it.key, it.value.status()) }.toTypedArray())
                  else mutableStateMapOf(*student.treatments.map { Pair(it.key, it.value.status()) }.toTypedArray())

               TTable(
                  TRow("סטטוס", "שם טיפול"),
                  *mapping.map { TRow({ it.value.i() }, { Text(it.key) }) }.toTypedArray(),
                  gModifier = Modifier.padding(5.dp),
                  hModifier = Modifier.height(40.dp),
                  rModifier = Modifier.height(30.dp),
               )
            }

         }
      }

   }

}