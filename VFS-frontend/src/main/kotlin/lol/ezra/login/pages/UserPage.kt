package lol.ezra.login.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import getUser
import kotlinx.coroutines.runBlocking

object UserPage : Screen {
   @Composable
   override fun Content() {
      val user = runBlocking { getUser() }
      MaterialTheme {
         Column {
            Row {
               Text("Hello, ${user.name} ${user.lastName}")
            }
         }
      }
   }


}