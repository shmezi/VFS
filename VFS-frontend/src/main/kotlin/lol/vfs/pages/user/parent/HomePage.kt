package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.assets.Status
import lol.vfs.db.users.Student
import lol.vfs.extensions.*
import lol.vfs.minilib.pq
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.panel.parent.ParentReminderMessage
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.pages.components.tile.StudentTile
import lol.vfs.utils.studentClass
import lol.vfs.utils.studentGrade

@Composable
fun RowScope.HomePage(kids: SnapshotStateList<Student>, kid: MutableState<Student?>, dialog: MutableState<Boolean>) {
   var kid by kid
   var dialog by dialog


   Column(modifier = Modifier.weight(4f)) {
      kid ?: return@Column
      var state by remember { mutableStateOf(true) }
      Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
         Text("תיק רפואי", fontSize = 45.sp)
      }
      5.h()
      Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
         ButtonSwitch {
            state = it

         }
      }




      Column {
         TTable(
            if (state)
               TRow("המלצות רופא", "תאריך בדיקה", "שם")
            else
               TRow("המלצות רופא", "תוצאה", "תאריך קבלת החיסון", "שם"),
            * (if (state) kid.rowifyTreatments() else kid.rowifyTests())
         )
      }


   }
   5.w()
   Column(modifier = Modifier.weight(3f)) {
      kid ?: return@Column
      ParentReminderMessage(kid?.treatmentStatus() == Status.APPROVED) {
         dialog = !dialog
      }
      TTable(
         TRow("אישור", "סוג", "שם"),
         *kid.rowifyApproval(false),
         hModifier = Modifier.height(40.dp),
         rModifier = Modifier.height(30.dp),
      )
   }
   Column(
      modifier = Modifier.weight(2f),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text("ילדים", fontSize = 30.sp)
      Column(Modifier.fillMaxSize().weight(3f).verticalScroll(rememberScrollState())) {
         kids.forEach {
            StudentTile(studentGrade[it]!!, studentClass[it]!!, it, kid) { s ->
               kid = s
            }
         }
      }

   }
}