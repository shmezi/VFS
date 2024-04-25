package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.model.users.Student
import lol.vfs.extensions.*
import lol.vfs.pages.components.button.ButtonSwitch
import lol.vfs.pages.components.panel.parent.ParentReminderMessage
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.pages.components.tile.StudentTile
import lol.vfs.pages.user.parent.Parent.selectedKid
import lol.vfs.styling

/**
 * The homepage for parents
 * @param kids The list of kids that are assigned to given parent
 */
@Composable
fun RowScope.HomePage(
   kids: SnapshotStateList<Student>,
   ) {
   var selectedKid by selectedKid

   //Selected kid/student info

   Row(modifier = Modifier.weight(0.9f)) {
      Column(modifier = Modifier.weight(4f)) {
         selectedKid ?: return@Column
         var state by remember { mutableStateOf(true) }
         Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(style = styling, overflow = TextOverflow.Ellipsis, text = "תיק רפואי", fontSize = 45.sp)
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
               * (if (state) selectedKid.rowifyTreatments() else selectedKid.rowifyTests())
            )
         }


      }
      5.w()
      Column(modifier = Modifier.weight(3f)) {
         val k = selectedKid ?: return@Column
         ParentReminderMessage(k)
         TTable(
            TRow("אישור", "סוג", "ילד", "תאריך"),
            *k.rowifyApproval(false),
            hModifier = Modifier.height(40.dp),
            rModifier = Modifier.height(30.dp),
         )
      }
      Column(
         modifier = Modifier.weight(2f),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Text(style = styling, overflow = TextOverflow.Ellipsis, text = "ילדים", fontSize = 30.sp)
         Column(Modifier.fillMaxSize().weight(3f).verticalScroll(rememberScrollState())) {
            for (k in kids) {
               StudentTile(k, selectedKid, false) {
                  selectedKid = it
               }
            }


         }

      }
   }
}