package lol.vfs.pages.user

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
import lol.vfs.ColorPallet
import lol.vfs.bg
import lol.vfs.getClasses
import lol.vfs.pages.components.ClassPanel
import lol.vfs.pages.components.StudentPanel
import lol.vfs.db.Student

object Doctor : Screen {
   @Composable
   override fun Content() {
      var selectedStudent by remember { mutableStateOf<Student?>(null) }

      val students = remember { mutableStateListOf<Student>() }

      Row(Modifier.fillMaxSize().bg(ColorPallet.BACKGROUNDP)) {
         Column(
            Modifier.weight(0.3f).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            getClasses().forEach {
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