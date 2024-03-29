package lol.vfs.pages.user.parent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lol.vfs.model.users.Student
import lol.vfs.extensions.rowifyApproval
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.pages.components.layout.table.TTable
import lol.vfs.styling

@Composable
fun RowScope.SchedulePage(kids: SnapshotStateList<Student>) {
   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.weight(0.9f)
   ) {
      Text(style = styling, overflow = TextOverflow.Ellipsis,text = "מועדי בדיקות וחיסונים", fontSize = 40.sp, textAlign = TextAlign.Center)
      val rows = mutableListOf<TRow>()
      kids.forEach {
         rows.addAll(it.rowifyApproval(true))
      }

      TTable(
         TRow("אושר", "אירוע", "ילד", "תאריך מועד"),
         * rows.toTypedArray(),
         hModifier = Modifier.height(40.dp),
         rModifier = Modifier.height(30.dp),
      )

   }
}