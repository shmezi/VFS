package lol.vfs.pages.components.layout

import androidx.compose.runtime.Composable

@Composable
fun <T> Switch(switch: Boolean, t: T, f: T) = if (switch) t else f