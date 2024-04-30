package lol.vfs.pages.user.parent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import lol.vfs.LocalCache.getStudentIds
import lol.vfs.LocalCache.getStudents
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.client
import lol.vfs.model.users.Student
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.nav.Nav
import lol.vfs.url


object Parent : Screen {

   var selectedKid = mutableStateOf<Student?>(null)

   @Composable
   override fun Content() {


      val kids = remember { mutableStateListOf<Student>() }
      runBlocking {
         client.get("studyData".url())
         kids.addAll(getStudents(getStudentIds()))
      }

      PageLayout {
         Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
            Nav {
               page("home", "home") {
                  HomePage(kids)
               }
               page("education") {
                  EducationPage()
               }
               page("schedule") {
                  SchedulePage(kids)
               }
            }
         }


      }


   }


}