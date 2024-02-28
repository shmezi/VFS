package lol.vfs.extensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Int.w() = Spacer(Modifier.width(this.dp))

@Composable
fun Int.h() = Spacer(Modifier.height(this.dp))

@Composable
fun Int.s() = Spacer(Modifier.size(this.dp))