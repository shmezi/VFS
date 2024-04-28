package lol.vfs.pages.user.parent

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import lol.vfs.pages.components.panel.parent.LearningPanel
import lol.vfs.styling
import lol.vfs.utils.LearnCenterData

@Preview
@Composable
fun EducationPage() {
   Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(style = styling, overflow = TextOverflow.Ellipsis, text = "מרכז למידה", fontSize = 50.sp)
      Row(Modifier.horizontalScroll(rememberScrollState(50))) {
         LearnCenterData.entries.map { it.learningMaterial }.toSet().forEach {
            LearningPanel(it)
         }
      }
   }
}