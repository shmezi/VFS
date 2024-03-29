package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import lol.vfs.*
import lol.vfs.LocalCache.getGradeIds
import lol.vfs.LocalCache.getGrades
import lol.vfs.LocalCache.getStudentIds
import lol.vfs.LocalCache.getStudents
import lol.vfs.assets.NavIcon
import lol.vfs.lib.printing.pq
import lol.vfs.model.StudyData
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
      runBlocking {
         client.get("studyData".url())
      }
      val kid = remember { mutableStateOf<Student?>(null) }
      val kids = remember { mutableStateListOf<Student>() }


      runBlocking {
         kids.addAll(getStudents(getStudentIds()))
      }


      val dialogState = remember { mutableStateOf(false) }

      var dialog by dialogState
      var markAll by mutableStateOf(false)
      PageLayout(runBlocking { getUser() }, dialogState, {
         Text(style = styling, overflow = TextOverflow.Ellipsis,text=
            "האם מילאתם את הצהרת הבריאות באתר משרד החינוך?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
         )
         LinkButton("https://parents.education.gov.il/prhnet/contact-us/confirmations/annual-health-statement") {
            Text(style = styling, overflow = TextOverflow.Ellipsis,text="לאישור באתר משרד החינוך")
         }
         Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
         ) {
            Button(
               {
                  dialog = false
                  kid.value?.treatments?.forEach {
                     it.value.approved = true
                  }
                  kid.value?.tests?.forEach {
                     it.value.approved = true
                  }
                  markAll = true
                  runBlocking { LocalCache.postToCloud(kid.value?.id ?: return@runBlocking) }
               },
            ) {
               Text(style = styling, overflow = TextOverflow.Ellipsis,text="כן, אישרתי הכל!")
            }
         }
      }) {

         Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
            var page by remember { mutableStateOf(NavIcon.HOME) }
            when (page) {
               NavIcon.HOME -> HomePage(kids, markAll, kid, dialogState)

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