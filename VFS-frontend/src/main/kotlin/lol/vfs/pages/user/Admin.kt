package lol.vfs.pages.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.assets.ColorPallet
import lol.vfs.assets.ColorPallet.Companion.bg
import lol.vfs.assets.Status
import lol.vfs.client
import lol.vfs.extensions.status
import lol.vfs.extensions.w
import lol.vfs.model.users.Student
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.layout.PageLayout
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.pages.components.nav.Nav
import lol.vfs.pages.components.panel.SelectionPanel
import lol.vfs.pages.components.tile.StudentInfoPanel
import lol.vfs.requests.importClassroomData
import lol.vfs.requests.importUsers
import lol.vfs.styling
import lol.vfs.url
import java.io.File


object Admin : Screen {

   @Composable
   override fun Content() {
      val studentState = remember { mutableStateOf<Student?>(null) }
      val student by studentState
      PageLayout {

         Nav {
            page("home") {
               Row(
                  Modifier.fillMaxSize().bg(ColorPallet.BG_A)
               ) {


                  //Student info panel
                  Column(Modifier.weight(2f).fillMaxHeight(), horizontalAlignment = Alignment.End) {
                     val student = student ?: return@Column
                     StudentInfoPanel(student)
                     var state by remember { mutableStateOf(true) }

                     Row {
                        ButtonSwitch {
                           state = it
                        }
                        5.w()
                     }
                     val mapping: SnapshotStateMap<String, Status> =
                        if (state) mutableStateMapOf(*student.tests.map { Pair(it.key, it.value.status()) }
                           .toTypedArray())
                        else mutableStateMapOf(*student.treatments.map { Pair(it.key, it.value.status()) }
                           .toTypedArray())

                     TTable(
                        TRow("סטטוס", "שם טיפול"),
                        *mapping.map {
                           TRow(
                              { it.value.image() },
                              { Text(style = styling, overflow = TextOverflow.Ellipsis, text = it.key) })
                        }.toTypedArray(),
                        gModifier = Modifier.padding(5.dp),
                        hModifier = Modifier.height(40.dp),
                        rModifier = Modifier.height(30.dp),
                     )
                  }
                  SelectionPanel(studentState)
               }
            }
            page("admin") {
               Column(
                  Modifier.fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Top
               ) {
                  Text("כלי מנהל", fontSize = 42.sp)
                  var filePicker by remember { mutableStateOf(false) }

                  var importing by remember { mutableStateOf(Importer.USER) }


                  Button({
                     importing = Importer.CLASSROOM
                     filePicker = true
                  }) {
                     Text("פתח קובץ תלמידים")
                  }
                  Button({
                     importing = Importer.USER
                     filePicker = true
                  }) {
                     Text("פתח קובץ משתמשים")
                  }

                  FilePicker(show = filePicker, fileExtensions = listOf("csv")) { file ->
                     filePicker = false
                     when(importing){
                        Importer.USER -> {
                           val s = importUsers(File(file?.path ?: return@FilePicker))
                           runBlocking {
                              client.post("import/users".url()) {
                                 contentType(ContentType.Application.Json)
                                 setBody(s)
                              }
                           }
                        }
                        Importer.CLASSROOM -> {
                           val s = importClassroomData(File(file?.path ?: return@FilePicker))
                           runBlocking {
                              client.post("import/classroom".url()) {
                                 contentType(ContentType.Application.Json)
                                 setBody(s)
                              }
                           }
                        }
                     }

                  }


               }
            }
         }

      }

   }

   enum class Importer {
      USER,
      CLASSROOM
   }
}