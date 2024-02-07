package lol.ezra.login.pages

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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import client
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object LoginPage : Screen {


   @Composable
   override fun Content() {
      MaterialTheme {


         Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
         ) {

            Text("Login")
            var message by remember { mutableStateOf("") }
            Text(message)
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
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
                  val status = client.post("http://localhost:8080/auth/login") {
                     parameter("username", username)
                     parameter("password", password)
                  }.status
                  if (status == HttpStatusCode.OK) {
                     navigator.push(UserPage)
                     return@runBlocking
                  }
                  if (status == HttpStatusCode.Forbidden) {
                     message = "Wrong credentials!"
                     delay(500)
                     message = ""
                  }
               }
            }) {
               Text("Login")
            }

         }
      }
   }

}


