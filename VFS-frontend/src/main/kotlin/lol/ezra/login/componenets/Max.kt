package lol.ezra.login.componenets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.reflect.ComposableMethod
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MaxRow(
   modifier: Modifier = Modifier,
   horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
   verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
   content: @Composable RowScope.() -> Unit
) = Row(modifier.fillMaxSize(), horizontalArrangement, verticalAlignment, content)

@Composable
fun MaxColumn(
   modifier: Modifier = Modifier,
   scrollable: Boolean = true,
   verticalArrangement: Arrangement.Vertical = Arrangement.Center,
   horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
   content: @Composable ColumnScope.() -> Unit
) = Column(
   modifier.fillMaxSize().verticalScroll(rememberScrollState(), scrollable),
   verticalArrangement,
   horizontalAlignment,
   content
)