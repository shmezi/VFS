package lol.vfs.pages.components.layout.footer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.assets.Status.*
import lol.vfs.extensions.w
import lol.vfs.model.users.UserType
import lol.vfs.styling

@Composable
fun Explanation(icon: Status) {
   Row(verticalAlignment = Alignment.CenterVertically) {
      Text(style = styling, overflow = TextOverflow.Ellipsis, text = icon.description)
      5.w()
      icon.image()
   }
}

@Composable
fun Explanations(vararg icons: Status) {
   Row(
      Modifier.fillMaxWidth(),
   ) {
      10.w()
      icons.forEach {
         Explanation(it)
         25.w()
         10.dp
      }
   }
}

@Composable
fun RowScope.AutoExplanations(type: UserType) {
   Column(
      Modifier.weight(8f),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      when (type) {
         UserType.ADMIN -> Explanations(APPROVED, DENIED, PARTIAL, DONE)

         UserType.PARENT -> Explanations(APPROVED, DENIED)

         UserType.DOCTOR -> Explanations(APPROVED, DENIED, DONE)
      }
   }
}