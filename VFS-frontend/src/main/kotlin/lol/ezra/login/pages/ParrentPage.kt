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
import lol.ezra.login.componenets.MaxRow
import lol.ezra.login.componenets.ResultPanel
import lol.ezra.login.componenets.UpcomingPanel

object ParrentPage : Screen {
   @OptIn(ExperimentalLayoutApi::class)
   @Composable
   override fun Content() {
      MaterialTheme {
         MaxRow {
            Column(
               modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Red).weight(0.6666666f)
            ) {
               Column(
                  modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Yellow).weight(0.6666666f)
                     .verticalScroll(
                        rememberScrollState()
                     )

               ) {
                  for (x in 0 .. 10) {
                     UpcomingPanel()
                  }
               }
               Column(
                  modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Magenta).weight(0.6666666f)
                     .verticalScroll(
                        rememberScrollState()
                     )
               ) {
                  for (x in 0 .. 10) {
                     ResultPanel()
                  }
               }
            }
            Column(
               modifier = Modifier.fillMaxWidth().background(Color.Green).fillMaxHeight().weight(0.3333333f)
                  .verticalScroll(
                     rememberScrollState()
                  )
            ) {
               Text("Learning")
               FlowColumn {
                  for (t in 1 .. 100) {
                     Text("T1", modifier = Modifier.size(Dp(100f)))
                  }
               }
            }
         }
      }

   }
}