package lol.vfs.pages.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.assets.Status
import lol.vfs.db.organizational.Class
import lol.vfs.db.users.Student
import lol.vfs.db.users.UserType
import lol.vfs.extensions.status
import lol.vfs.extensions.w
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.panel.*
import lol.vfs.pages.components.table.Row
import lol.vfs.pages.components.table.TTable
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
            Modifier.fillMaxSize().bg(ColorPallet.BACKGROUNDP)
         ) {
            //Grade panels
            Column(
               Modifier.fillMaxHeight().verticalScroll(rememberScrollState()).weight(1f),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               grades.forEach { g ->
                  GradePanel(g) { grade, selected ->
                     if (selected) classes.addAll(grade.classes)
                     else {
                        grade.classes.forEach { clazz ->
                           if (clazz.students.firstOrNull { it == student } != null)
                              student = null
                           students.removeAll(clazz.students)
                        }
                        classes.removeAll(grade.classes)

                     }


                  }
               }
            }
            //Class panels
            Column(
               Modifier.weight(1f).verticalScroll(rememberScrollState()),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               classes.forEach {
                  ClassPanel(it) { clazz, selected ->
                     if (selected) students.addAll(clazz.students)
                     else {
                        students.removeAll(clazz.students)
                        if (clazz.students.firstOrNull { it == student } != null)
                           student = null
                     }

                  }
               }
            }
            //Student panels
            Column(
               Modifier.weight(1.5f).verticalScroll(rememberScrollState()),
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
            //Student info panel
            Column(Modifier.weight(2f).fillMaxHeight(), horizontalAlignment = Alignment.End) {
               val student = student ?: return@Column
               StudentInfoPanel(student)
               var state by remember { mutableStateOf(false) }

               Row {
                  ButtonSwitch {
                     state = it
                  }
                  5.w()
               }
               val mapping: SnapshotStateMap<String, Status> =
                  if (!state) mutableStateMapOf(*student.tests.map { Pair(it.key, it.value.status()) }.toTypedArray())
                  else mutableStateMapOf(*student.treatments.map { Pair(it.key, it.value.status()) }.toTypedArray())
               TTable(
                  Row({ Text("סטטוס") }, { Text("שם טיפול") }),
                  hModifier = Modifier.height(40.dp),
                  rModifier = Modifier.height(30.dp),
                  gModifier = Modifier.padding(5.dp),
                  rows = mapping.map { Row({ it.value.i() }, { Text(it.key) }) }.toTypedArray()
               )
            }

         }
      }

   }

}