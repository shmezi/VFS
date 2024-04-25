package lol.vfs.pages.components.layout

import androidx.compose.runtime.Composable

/**
 * Switches between two items based on given boolean type
 */
@Composable
fun <T> Boolean.Switch(t: T, f: T) = if (this) t else f