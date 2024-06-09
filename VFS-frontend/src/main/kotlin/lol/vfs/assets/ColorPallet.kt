package lol.vfs.assets

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * The color pallet of the entire program (Theoretically)
 * @param r Red value of the color ( 0 - 255 )
 * @param g Green value of the color ( 0 - 255 )
 * @param b Blue value of the color ( 0 - 255 )
 */
enum class ColorPallet(
   r: Int,
   g: Int,
   b: Int,
) {
   BG_A(199, 205, 217),
   PRIMARY(191, 4, 54),
   TXT_A(1, 58, 64),

   BG_B(137, 171, 217),
   TXT_B(22, 158, 242);

   /**
    * The [Color] representation of a given color pallet color.
    */
   val color = Color(r, g, b)

   companion object {
      /**
       * Modifier extension allowing to add a background color easily to components.
       */
      fun Modifier.bg(color: ColorPallet) = background(color.color)

   }
}

