package lol.vfs.pages.components.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler

@Composable
fun LinkButton(url: String, modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
   val opener = LocalUriHandler.current
   Button({
      opener.openUri(url)
   }, modifier = modifier, content = content)
}