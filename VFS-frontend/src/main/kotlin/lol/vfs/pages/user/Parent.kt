package lol.vfs.pages.user

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import lol.vfs.assets.NavIcon
import lol.vfs.db.Parent
import lol.vfs.db.Student
import lol.vfs.db.UserType
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.layout.NavBar
import lol.vfs.pages.components.panel.StudentPanel
import lol.vfs.requests.UserRequest
import lol.vfs.utils.grades
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade
import lol.vfs.utils.students


object Parent : Screen {
   @Composable
   override fun Content() {
      var student by remember { mutableStateOf<Student?>(null) }
      PageLayout(UserRequest("337616346", "עזרא", "גולומבק", UserType.PARENT)) {
         Row(modifier = Modifier.fillMaxSize().background(Color.Red), horizontalArrangement = Arrangement.End) {
            var page by remember { mutableStateOf(NavIcon.HOME) }
            when (page) {
               NavIcon.HOME -> {
                  Column(modifier = Modifier.weight(4f)) {
                  }
                  Column(
                     modifier = Modifier.weight(2f),
                     horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                     Text("תלמידים", fontSize = 30.sp)
                     Column(Modifier.fillMaxSize().weight(3f).verticalScroll(rememberScrollState())) {
                        grades[0].classes.first().students.forEach {
                           StudentPanel(studentGrade[it]!!, studentClass[it]!!, it, student) { s ->
                              student = s
                           }
                        }
                     }

                  }
               }

               NavIcon.EDU -> {
                  Column(horizontalAlignment = Alignment.CenterHorizontally) {
                     Text("מרכז למידה", fontSize = 50.sp)
                     Row(Modifier.horizontalScroll(rememberScrollState())) {

                     }
                  }
               }

               NavIcon.SCHEDULE -> {

               }
            }

            NavBar {
               page = it
            }


         }
      }
   }


}