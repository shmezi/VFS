package lol.vfs.pages.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import lol.vfs.assets.NavIcon


@Composable
fun IButton(icon: NavIcon, selected: NavIcon, onClick: (NavIcon) -> Unit) {
   IconButton(
      modifier = Modifier.background(if (icon == selected) Color.DarkGray else Color.Gray),
      onClick = { onClick(icon) }) {
      icon.i()
   }
}

@Composable
fun NavBar(update: (NavIcon) -> Unit) {
   Column(Modifier.fillMaxHeight().background(Color.Gray)) {
      var icon by remember { mutableStateOf(NavIcon.HOME) }
      NavIcon.entries.forEach { nav ->
         IButton(nav, icon) {
            icon = it
            update(icon)
         }
      }


   }
}