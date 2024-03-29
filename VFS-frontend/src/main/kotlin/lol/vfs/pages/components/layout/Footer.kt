package lol.vfs.pages.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.assets.Status.*
import lol.vfs.model.users.UserType
import lol.vfs.extensions.studyImage
import lol.vfs.extensions.w
import lol.vfs.requests.UserRequest
import lol.vfs.styling
import java.time.LocalDateTime


@Composable
fun User(user: UserRequest) {
   val now = LocalDateTime.now()
   Row(
      verticalAlignment = Alignment.CenterVertically,

      ) {

      Column(horizontalAlignment = Alignment.End) {
         Text(style = styling, overflow = TextOverflow.Ellipsis,text="${user.name} ${user.lastName}", textAlign = TextAlign.Left)
         Text(style = styling, overflow = TextOverflow.Ellipsis,text="${now.year}/${now.month.value}/${now.dayOfMonth}", textAlign = TextAlign.Left)
      }

      when (user.type) {
         UserType.ADMIN -> UserType.ADMIN
         UserType.PARENT -> UserType.PARENT
         UserType.DOCTOR -> UserType.DOCTOR
      }.studyImage()

   }
}

@Composable
fun Explanation(icon: Status) {
   Row(verticalAlignment = Alignment.CenterVertically) {
      Text(style = styling, overflow = TextOverflow.Ellipsis,text=icon.description)
      5.w()
      icon.i()
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
fun AutoExplanations(type: UserType) {
   when (type) {
      UserType.ADMIN -> Explanations(APPROVED, DENIED, PARTIAL, DONE)

      UserType.PARENT -> Explanations(APPROVED, DENIED)

      UserType.DOCTOR -> Explanations(APPROVED, DENIED, DONE)
   }
}

@Composable
fun Footer(user: UserRequest) {
   Row(
      Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End
   ) {
      Column(
         Modifier.weight(8f),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         AutoExplanations(user.type)
      }

      Column(
         Modifier.weight(2f),
         horizontalAlignment = Alignment.End
      ) {
         User(user)
      }

   }
}