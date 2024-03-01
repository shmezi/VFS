package lol.vfs.pages.user.parent

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import lol.vfs.assets.NavIcon
import lol.vfs.assets.Status
import lol.vfs.db.users.Student
import lol.vfs.db.users.UserType
import lol.vfs.extensions.medsAsParentRows
import lol.vfs.extensions.medsAsStatusRows
import lol.vfs.extensions.treatmentStatus
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.NavBar
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.panel.StudentPanel
import lol.vfs.pages.components.panel.parent.LearningPanel
import lol.vfs.pages.components.panel.parent.ParentReminderMessage
import lol.vfs.pages.components.table.Row
import lol.vfs.pages.components.table.TTable
import lol.vfs.requests.UserRequest
import lol.vfs.utils.grades
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade
import lol.vfs.utils.studyDataList


object Parent : Screen {
   @Composable
   override fun Content() {
      var kid by remember { mutableStateOf<Student?>(null) }
      val kids = remember { mutableStateListOf(*grades[0].classes.first().students.toTypedArray()) }
      var dialog by remember { mutableStateOf(false) }
      PageLayout(UserRequest("337616346", "עזרא", "גולומבק", UserType.PARENT), {
         if (dialog) {
            Column(Modifier.fillMaxSize().background(color = Color.Blue.copy(0.8f))) {
               Dialog(onDismissRequest = {
                  dialog = false
               }, content = {
                  Column(
                     Modifier.fillMaxHeight(0.6f).fillMaxWidth(1f).clip(RoundedCornerShape(7))
                        .border(border = BorderStroke(2.dp, Color.Black)).background(Color.White),
                     horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                     Text(
                        "האם מילאתם את הצהרת הבריאות באתר משרד החינוך?",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                     )
                     val uriHandler = LocalUriHandler.current
                     Button({

                        uriHandler.openUri("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                     }) {
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

                  }

               })
            }
         }
      }) {
         Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
            var page by remember { mutableStateOf(NavIcon.HOME) }
            when (page) {
               NavIcon.HOME -> {
                  Column(modifier = Modifier.weight(4f)) {
                     var state by remember { mutableStateOf(false) }
                     ButtonSwitch {
                        state = it
                     }

                  }
                  Column(modifier = Modifier.weight(3f)) {
                     kid ?: return@Column
                     ParentReminderMessage(kid?.treatmentStatus() == Status.APPROVED) {
                        dialog = !dialog
                     }
                     TTable(
                        Row({ Text("אישור") }, { Text("שם") }, { Text("סוג") }),
                        hModifier = Modifier.height(40.dp),
                        rModifier = Modifier.height(30.dp),
                        rows = kid?.medsAsParentRows()?.toTypedArray() ?: arrayOf()
                     )
                  }
                  Column(
                     modifier = Modifier.weight(2f),
                     horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                     Text("ילדים", fontSize = 30.sp)
                     Column(Modifier.fillMaxSize().weight(3f).verticalScroll(rememberScrollState())) {
                        kids.forEach {
                           StudentPanel(studentGrade[it]!!, studentClass[it]!!, it, kid) { s ->
                              kid = s
                           }
                        }
                     }

                  }
               }

               NavIcon.EDU -> {
                  Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(0.9f)) {
                     Text("מרכז למידה", fontSize = 50.sp)
                     Row(Modifier.horizontalScroll(rememberScrollState(50))) {
                        studyDataList.forEach {
                           LearningPanel(it) {

                           }
                        }
                     }
                  }
               }

               NavIcon.SCHEDULE -> {

                  Column(
                     horizontalAlignment = Alignment.CenterHorizontally,
                     modifier = Modifier.weight(0.9f)
                  ) {
                     Text("מועדי בדיקות וחיסונים", fontSize = 40.sp, textAlign = TextAlign.Center)
                     val rows = mutableListOf<Row>()
                     for (k in kids) {
                        k.medsAsStatusRows()
                     }
                     TTable(
                        Row({ Text("אושר") }, { Text("אירוע") }, { Text("ילד") }),
                        Modifier.fillMaxSize(),
                        hModifier = Modifier.height(40.dp),
                        rModifier = Modifier.height(30.dp),
                        rows = rows.toTypedArray()
                     )
                  }
               }
            }

            NavBar {
               page = it
            }


         }
      }
   }


}