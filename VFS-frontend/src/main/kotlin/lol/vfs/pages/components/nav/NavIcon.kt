package lol.vfs.pages.components.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class NavIcon(private val image: String, val description: String, val selectPage: @Composable () -> Unit) {
   @Composable
   fun resource() = painterResource("assets/navigation/$image.png")

   @Composable
   fun image() = Image(resource(), description, contentScale = ContentScale.Fit, modifier = Modifier.size(20.dp))
}