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
import lol.vfs.LocalCache.getGradeIds
import lol.vfs.LocalCache.getGrades
import lol.vfs.LocalCache.getStudentIds
import lol.vfs.LocalCache.getStudents
import lol.vfs.assets.NavIcon
import lol.vfs.getUser
import lol.vfs.lib.printing.pq
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.Student
import lol.vfs.model.users.UserType
import lol.vfs.pages.components.button.LinkButton
import lol.vfs.pages.components.layout.NavBar
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.requests.UserRequest


object Parent : Screen {
   @Composable
   override fun Content() {
      val kid = remember { mutableStateOf<Student?>(null) }
      val kids = remember { mutableStateListOf<Student>() }


      runBlocking {
         kids.addAll(getStudents(getStudentIds().pq("IDS")).pq("Students"))
         kids.forEach {
            it.pq("Student")
         }
      }


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