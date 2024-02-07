package lol.ezra.login.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import cafe.adriel.voyager.core.screen.Screen
import getUser
import kotlinx.coroutines.runBlocking
import lol.ezra.login.componenets.*

object TeacherPage : Screen {
   @OptIn(ExperimentalLayoutApi::class)
   @Composable
   override fun Content() {
      val user = runBlocking { getUser() }
      MaterialTheme {
         MaxRow {
            Column(
               modifier = Modifier.fillMaxWidth().background(Color.Green).fillMaxHeight().weight(0.3333333f)

            ) {
               Text("Classes")
               FlowColumn(
                  modifier = Modifier.verticalScroll(
                     rememberScrollState()
                  ).fillMaxWidth()
               ) {
                  for (t in 1 .. 100) {
//                     ClassTile()
                  }
               }
            }
            Column(
               modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Red).weight(0.6666666f)
            ) {
               Text("Students")
               Column(
                  modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Magenta)
                     .verticalScroll(
                        rememberScrollState()
                     )
               ) {
                  for (x in 0 .. 100) {
                     ResultPanel()
                  }
               }
            }

         }
      }

   }


}