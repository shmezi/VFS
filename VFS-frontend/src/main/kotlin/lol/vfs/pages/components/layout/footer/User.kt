package lol.vfs.pages.components.layout.footer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import lol.vfs.client
import lol.vfs.extensions.studyImage
import lol.vfs.model.users.UserType
import lol.vfs.pages.auth.Login
import lol.vfs.requests.UserRequest
import lol.vfs.styling
import lol.vfs.url
import java.time.LocalDateTime

@Composable
fun RowScope.User(user: UserRequest) {
   val navigator = LocalNavigator.currentOrThrow

   Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End,
      //Logout on click
      modifier = Modifier.clickable {
         runBlocking {
            client.post("auth/logout".url())
            navigator.push(Login)
         }
      }.weight(2f)


   ) {
      Column(horizontalAlignment = Alignment.End) {
         //User
         Text(
            style = styling,
            overflow = TextOverflow.Ellipsis,
            text = "${user.name} ${user.lastName}",
            textAlign = TextAlign.Left
         )
         //User's ID
         Text(
            style = styling,
            overflow = TextOverflow.Ellipsis,
            text = user.id,
            textAlign = TextAlign.Left
         )
      }
      //Profile image for type
      when (user.type) {
         UserType.ADMIN -> UserType.ADMIN
         UserType.PARENT -> UserType.PARENT
         UserType.DOCTOR -> UserType.DOCTOR
      }.studyImage()

   }
}