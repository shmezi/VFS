package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.NavIcon
import lol.vfs.getUser
import lol.vfs.model.users.Student
import lol.vfs.model.users.UserType
import lol.vfs.pages.components.button.LinkButton
import lol.vfs.pages.components.layout.NavBar
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.requests.UserRequest
import lol.vfs.utils.grades


object Parent : Screen {
   @Composable
   override fun Content() {
      val kid = remember { mutableStateOf<Student?>(null) }
      val kids = remember { mutableStateListOf(*grades[0].classes.first().students.toTypedArray()) }
      val dialogState = remember { mutableStateOf(false) }

      var dialog by dialogState
      PageLayout(runBlocking { getUser() }, dialogState, {
         Text(
            "האם מילאתם את הצהרת הבריאות באתר משרד החינוך?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
         )
         LinkButton("https://www.youtube.com/watch?v=dQw4w9WgXcQ") {
            Text("לאישור באתר משרד החינוך")
         }
         Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
         ) {
            Button(
               {
                  dialog = false
               },
            ) {
               Text("כן, אישרתי!")
            }
         }
      }) {

         Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
            var page by remember { mutableStateOf(NavIcon.HOME) }
            when (page) {
               NavIcon.HOME -> HomePage(kids, kid, dialogState)

               NavIcon.EDU -> EducationPage()

               NavIcon.SCHEDULE -> SchedulePage(kids)

            }
            NavBar {
               page = it
            }


         }
      }
   }


}