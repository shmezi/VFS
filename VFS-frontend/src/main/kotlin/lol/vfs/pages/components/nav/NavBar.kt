package lol.vfs.pages.components.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import lol.vfs.extensions.w
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
      Row {
         var page by remember { mutableStateOf<NavIcon?>(null) }
         if (!hasRunContext) {
            context()
            page = icons.getOrNull(0)
            hasRunContext = true
         }
         Column(Modifier.weight(9f).fillMaxSize()) { page?.selectPage?.invoke() }
         5.w()
         Column(
            Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
         ) {
            if (icons.isEmpty()) return
            icons.forEach { nav ->
               IButton(nav, page) {
                  page = it
               }
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