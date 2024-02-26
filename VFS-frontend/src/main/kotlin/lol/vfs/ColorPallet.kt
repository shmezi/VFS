package lol.vfs

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


enum class ColorPallet(
   r: Int,
   g: Int,
   b: Int,
) {
   BACKGROUNDP(226, 252, 214),
   PRIMARY(20, 150, 127),
   TEXTP(9, 93, 126),
   BACKGROUNDS(204, 236, 238),
   SECONDARY(241, 249, 255);

   val c = Color(r, g, b)
}

fun Modifier.bg(color: ColorPallet) = background(color.c)
