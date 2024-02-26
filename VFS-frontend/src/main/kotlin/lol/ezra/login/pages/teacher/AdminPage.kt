package lol.ezra.login.pages.teacher

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import lol.ezra.Student
import lol.ezra.login.ColorPallet
import lol.ezra.login.bg
import lol.ezra.utils.classes

object AdminPage : Screen {
   @Composable
   override fun Content() {
      var selectedStudent by remember { mutableStateOf<Student?>(null) }

      val students = remember { mutableStateListOf<Student>() }

      Row(Modifier.fillMaxSize().bg(ColorPallet.BACKGROUNDP)) {
         Column(
            Modifier.weight(0.3f).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            classes.forEach {
               ClassPanel(it) { clazz, selected ->
                  if (selected.value) students.addAll(clazz.students)
                  else students.removeAll(clazz.students)
               }
            }
         }
         Column(
            Modifier.weight(0.3f).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            students.forEach {
               StudentPanel(it, selectedStudent) { student ->
                  selectedStudent = student
               }
            }
         }
         Column(Modifier.weight(0.3f)) {
            Text("Name: ${selectedStudent?.name ?: "None selected"} ${selectedStudent?.lastName ?: ""}")
            Text("ID: ${selectedStudent?.id ?: "None selected"}")

         }
      }

   }


}