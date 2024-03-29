package lol.vfs.pages.user.parent

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import lol.vfs.pages.components.panel.parent.LearningPanel
import lol.vfs.utils.SDS


@Composable
fun RowScope.EducationPage() {
   Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(0.9f)) {
      Text("מרכז למידה", fontSize = 50.sp)
      Row(Modifier.horizontalScroll(rememberScrollState(50))) {
         SDS.studyDataList.forEach {
            LearningPanel(it) {

            }
         }
      }
   }
}