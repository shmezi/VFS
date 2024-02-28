package lol.vfs.pages.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.db.Class
import lol.vfs.db.Student
import lol.vfs.db.UserType
import lol.vfs.extensions.AutosizeText
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.panel.ClassPanel
import lol.vfs.pages.components.panel.GradePanel
import lol.vfs.pages.components.panel.StudentPanel
import lol.vfs.requests.UserRequest
import lol.vfs.utils.grades
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade


object Admin : Screen {

   @Composable
   override fun Content() {
      val classes = remember { mutableStateListOf<Class>() }
      val students = remember { mutableStateListOf<Student>() }
      var student by remember { mutableStateOf<Student?>(null) }
      PageLayout(UserRequest("337616346", "עזרא", "גולומבק", UserType.DOCTOR)) {
         Row(
            Modifier.fillMaxSize()
               .bg(ColorPallet.BACKGROUNDP)
         ) {
            Column(
               Modifier
                  .fillMaxHeight()
                  .verticalScroll(rememberScrollState())
                  .weight(1f),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               grades.forEach { g ->
                  GradePanel(g) { grade, selected ->
                     if (selected)
                        classes.addAll(grade.classes)
                     else {
                        grade.classes.forEach { students.removeAll(it.students) }
                        classes.removeAll(grade.classes)
                     }

                  }
               }
            }
            Column(
               Modifier.weight(1f).verticalScroll(rememberScrollState()),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               classes.forEach {
                  ClassPanel(it) { clazz, selected ->
                     if (selected)
                        students.addAll(clazz.students)
                     else
                        students.removeAll(clazz.students)

                  }
               }
            }
            Column(
               Modifier.weight(2f)
                  .verticalScroll(rememberScrollState())
               ,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               students.forEach {
                  StudentPanel(
                     studentGrade[it]!!, studentClass[it]!!, it, student
                  ) { a ->
                     student = a
                  }
               }
            }
            Column(
               Modifier.weight(2f).fillMaxSize().verticalScroll(rememberScrollState()),
               horizontalAlignment = Alignment.End

            ) {
               val s = student ?: return@Column
               Text("${s.name} ${s.lastName}", fontSize = 50.sp, fontWeight = FontWeight.Bold)
               Text(s.id, fontSize = 30.sp, fontWeight = FontWeight.Bold)

            }
         }
      }

   }

}