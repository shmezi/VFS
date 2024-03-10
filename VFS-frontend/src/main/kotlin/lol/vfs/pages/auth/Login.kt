package lol.vfs.pages.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.ColorPallet
import lol.vfs.client
import lol.vfs.model.users.UserType
import lol.vfs.getUser
import lol.vfs.minilib.pq
import lol.vfs.pages.user.Admin
import lol.vfs.pages.user.doctor.Doctor
import lol.vfs.pages.user.parent.Parent
import lol.vfs.requests.LoginRequest
import lol.vfs.url

object Login : Screen {


   @Composable
   override fun Content() {
      MaterialTheme {

         Column(
            modifier = Modifier.fillMaxSize().background(ColorPallet.BACKGROUNDS.c),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text("Login", fontSize = 45.sp)

            var username by remember { mutableStateOf("shmezi") }
            var password by remember { mutableStateOf("123456789") }
            TextField(username, {
               username = it
            }, placeholder = {
               Text("Username")
            })
            TextField(password, {
               password = it
            }, placeholder = { Text("Password") }, visualTransformation = PasswordVisualTransformation()
            )
            val navigator = LocalNavigator.currentOrThrow
            Button({
               runBlocking {
                  val call = client.post("auth/login".url()) {
                     contentType(ContentType.Application.Json)
                     setBody(LoginRequest(username, password))
                  }
                  val status = call.status
                  if (status == HttpStatusCode.OK) {
                     navigator.push(
                        when (getUser().type) {
                           UserType.ADMIN -> Admin
                           UserType.PARENT -> Parent
                           UserType.DOCTOR -> Doctor
                        }
                     )
                     return@runBlocking
                  } else {
                     "No login".pq()
                  }

               }
            }) {
               Text("Login")
            }
            Button({navigator.push(Register)}){ Text("Register")}

         }
      }
   }

}


