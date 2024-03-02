package lol.vfs.pages.user.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.db.users.Student
import lol.vfs.getUser
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.panel.SelectionPanel
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
               Modifier.weight(5f),
               horizontalAlignment = Alignment.End
            ) {
               student ?: return@Column
               StudentInfoPanel(student)
               ButtonSwitch {
                  state = it
               }
            }
         }
      }

   }

}