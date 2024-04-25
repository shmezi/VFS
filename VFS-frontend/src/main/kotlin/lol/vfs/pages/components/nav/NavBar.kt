package lol.vfs.pages.components.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import lol.vfs.lib.printing.pq


@Composable
fun IButton(icon: NavIcon, selected: NavIcon?, onClick: (NavIcon) -> Unit) {
   IconButton(
      modifier = Modifier.background(if (icon == selected) Color.DarkGray else Color.Gray),
      onClick = { onClick(icon) }) {
      icon.image()
   }
}


class Navigation(private val context: @Composable (Navigation.() -> Unit)) {
   private val icons = mutableListOf<NavIcon>()
   fun page(icon: String, description: String? = null, context: @Composable (() -> Unit)) {
      icons.add(NavIcon(icon, description ?: icon, context))
   }

   private var hasRunContext = false

   @Composable
   fun build() {
      var page by remember { mutableStateOf<NavIcon?>(null) }
      if (!hasRunContext) {
         context()
         "Context built!".pq()
         page = icons.getOrNull(0)
         hasRunContext = true
      }
      page?.selectPage?.invoke()

      Column(Modifier.fillMaxHeight().background(Color.Gray)) {
         if (icons.isEmpty()) return
         icons.forEach { nav ->
            IButton(nav, page) {
               page = it
               "Clicked a button".pq(nav.description)
            }
         }


      }


   }
}

@Composable
fun Nav(context: @Composable (Navigation.() -> Unit)) {
   val s by remember { mutableStateOf(Navigation(context)) }
   s.build()
}