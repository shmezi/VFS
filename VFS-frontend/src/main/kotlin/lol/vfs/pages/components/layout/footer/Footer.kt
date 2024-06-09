package lol.vfs.pages.components.layout.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.requests.UserRequest

@Composable
fun ColumnScope.Footer(user: UserRequest) {
   Row(
      Modifier.fillMaxWidth().weight(1f).bg(ColorPallet.BG_B),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End
   ) {
      AutoExplanations(user.type)
       User(user)

   }
}