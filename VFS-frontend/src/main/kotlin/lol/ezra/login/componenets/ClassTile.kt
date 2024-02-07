package lol.ezra.login.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ClassTile(id: String, memberCount: Int) {
   MaterialTheme {
      var name by remember { mutableStateOf(id) }
      var count by remember { mutableStateOf(memberCount) }

      Column(modifier = Modifier.fillMaxSize()) {
         Text("Class $name")
         Text("Has $count students")
      }

   }
}