package lol.vfs.pages.auth

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.client
import lol.vfs.url
import lol.vfs.model.users.UserType
import lol.vfs.lib.printing.pq
import lol.vfs.requests.RegisterRequest
import lol.vfs.styling

object Register : Screen {
   @Composable
   override fun Content() {
      val navigator = LocalNavigator.currentOrThrow

      var name by remember { mutableStateOf("") }
      var last by remember { mutableStateOf("") }
      var id by remember { mutableStateOf("") }

      var password by remember { mutableStateOf("") }
      var password2 by remember { mutableStateOf("") }


      var expanded by remember { mutableStateOf(false) }
      var selection by remember { mutableStateOf<UserType?>(null) }
      Column(
         Modifier.horizontalScroll(rememberScrollState()).fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center,
      ) {
         Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Register")
         TextField(name, {
            name = it
         }, placeholder = {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Name")
         })

         TextField(last, {
            last = it
         }, placeholder = {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Last name")
         })
         TextField(id, {
            id = it
         }, placeholder = {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "ID")
         })

         TextField(password,
            {
               password = it
            },
            placeholder = { Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Password") },
            visualTransformation = PasswordVisualTransformation()
         )
         TextField(password2,
            {
               password2 = it
            },
            placeholder = { Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Repeat password") },
            visualTransformation = PasswordVisualTransformation()
         )
         Box {
            IconButton({ expanded = true }) {
               Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                  Text(
                     style = styling,
                     overflow = TextOverflow.Ellipsis,
                     text = "${selection?.prettyPrint() ?: "Select type"} "
                  )
                  Icon(painterResource("assets/arrow.png"), "arrow", Modifier.size(10.dp))
               }
            }
            DropdownMenu(expanded = expanded, {
               expanded = false
            }) {
               UserType.entries.forEach {
                  DropdownMenuItem({
                     selection = it
                  }) {
                     Text(style = styling, overflow = TextOverflow.Ellipsis, text = it.prettyPrint())
                  }
               }
            }
         }

         Button({
            runBlocking {
               client.post("auth/register".url()) {
                  contentType(ContentType.Application.Json)
                  setBody(
                     RegisterRequest(
                        id, name, last, selection.pq("SELECTION") ?: return@runBlocking, password
                     )

                  )
               }
            }
            navigator.push(Login)


         }) {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "Register")
         }
      }
   }
}