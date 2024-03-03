package lol.vfs.pages.user.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.assets.Status
import lol.vfs.db.users.Student
import lol.vfs.extensions.*
import lol.vfs.getUser
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.layout.Switch
import lol.vfs.pages.components.panel.SelectionPanel
import lol.vfs.pages.components.panel.parent.ParentReminderMessage
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.pages.components.tile.StudentInfoPanel


object Doctor : Screen {

   @Composable
   override fun Content() {
      val studentState = remember { mutableStateOf<Student?>(null) }
      val student by studentState
      PageLayout(runBlocking { getUser() }) {
         Row(
            Modifier.fillMaxSize().bg(ColorPallet.BACKGROUNDP)
         ) {
            var state by remember { mutableStateOf(true) }
            SelectionPanel(studentState, sWeight = 3f, showStatus = false)
            Column(
               Modifier.weight(7f),
               horizontalAlignment = Alignment.End
            ) {
               val s = student ?: return@Column
               StudentInfoPanel(student)
               ButtonSwitch {
                  state = it
               }
               TTable(
                  Switch(
                     state,
                     TRow("המלצות", "תוצאות", "בוצע", "סטטוס", "שם בדיקה"),
                     TRow("תופעות לוואי", "בוצע", "סטטוס", "שם טיפול")
                  ),
                  *Switch(
                     state,
                     s.rowifyDocTests(),
                     s.rowifyDocTreatments()
                  ),
                  hModifier = Modifier.height(40.dp),
                  rModifier = Modifier.height(50.dp),
               )
            }
         }
      }

   }

}