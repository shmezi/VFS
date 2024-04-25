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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.*
import lol.vfs.assets.ColorPallet
import lol.vfs.model.users.UserType
import lol.vfs.pages.user.Admin
import lol.vfs.pages.user.doctor.Doctor
import lol.vfs.pages.user.parent.Parent
import lol.vfs.requests.LoginRequest
import lol.vfs.requests.UserRequest
import java.util.Timer
import kotlin.concurrent.timerTask

object Login : Screen {


   @Composable
   override fun Content() {
      MaterialTheme {
         Column(
            modifier = Modifier.fillMaxSize().background(ColorPallet.BG_B.color),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
         ) {


            var error by remember { mutableStateOf("") }

            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "התחברות", fontSize = 45.sp)
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = error, color = Color.Red, fontSize = 25.sp)
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(username, {
               username = it
            }, placeholder = {
               Text(style = styling, overflow = TextOverflow.Ellipsis, text = "תעודת זהות")
            })
            TextField(
               password,
               {
                  password = it
               },
               placeholder = { Text(style = styling, overflow = TextOverflow.Ellipsis, text = "סיסמה") },
               visualTransformation = PasswordVisualTransformation()
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
                     setUser(call.body<UserRequest>())
                     navigator.push(
                        when (getUser().type) {
                           UserType.ADMIN -> Admin
                           UserType.PARENT -> Parent
                           UserType.DOCTOR -> Doctor
                        }
                     )
                  } else {
                     error = "שם משתמש או סיסמה שהוזנו אינם נכונים"
                     Timer().schedule(timerTask {
                        error = ""
                     }, 3000L)
                     return@runBlocking
                  }


               }
            }) {
               Text(style = styling, overflow = TextOverflow.Ellipsis, text = "התחבר")
            }
            Button({ navigator.push(Register) }) {
               Text(
                  style = styling,
                  overflow = TextOverflow.Ellipsis,
                  text = "צור משתמש חדש"
               )
            }

         }
      }
   }

}


