package lol.vfs.pages.user

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import lol.vfs.pages.components.table.TRow
import lol.vfs.pages.components.table.TTable

object User : Screen {
   @Composable
   override fun Content() {
      var changing = remember { mutableStateListOf<TRow>() }
      TTable(
         TRow("Hello", "Hello"),
         *changing.toTypedArray()
      )
      Button({
         changing.clear()
         changing.add(TRow("Boob", "Funny"))
         changing.add(TRow("asd", "asd"))
      }) {
         Text("Click")
      }

   }
}