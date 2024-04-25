package lol.vfs.pages.components.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.getUser
import lol.vfs.pages.components.layout.footer.Footer
import lol.vfs.requests.UserRequest

/**
 * The layout used throughout all the pages
 */
@Composable
fun PageLayout(
   scope: @Composable ColumnScope.() -> Unit
) {
   Box {
      Column(Modifier.fillMaxSize()) {
         Column(Modifier.weight(10f).fillMaxWidth()) {
            scope()

         }
         Footer(getUser())
      }
   }
}