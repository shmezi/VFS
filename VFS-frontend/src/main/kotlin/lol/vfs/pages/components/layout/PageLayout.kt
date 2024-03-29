package lol.vfs.pages.components.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.requests.UserRequest

@Composable
fun PageLayout(
   user: UserRequest,
   dialogOpen: MutableState<Boolean>? = null,
   dialog: @Composable ColumnScope.() -> Unit = {},
   scope: @Composable ColumnScope.() -> Unit
) {
   Box {
      Column(Modifier.fillMaxSize()) {
         Column(Modifier.weight(10f).fillMaxWidth()) {
            scope()

         }
         Column(Modifier.bg(ColorPallet.TEXTP).weight(1f), verticalArrangement = Arrangement.SpaceEvenly) {
            Footer(user)
         }
      }
      if (dialogOpen?.value == true) {
         DialogPupUp(dialogOpen, dialog)
      }
   }
}