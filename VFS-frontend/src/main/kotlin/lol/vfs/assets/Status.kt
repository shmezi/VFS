package lol.vfs.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Status icons for the status of various tests
 * @param description A short description of the meaning of the icons
 */
enum class Status(val description: String) {
   /**
    * Indicating that a test has been done / vaccine given etc.
    */
   DONE("סוים"),

   /**
    * Indicates the parent has not responded / denied the given treatment/test
    */
   DENIED("לא אושר"),

   /**
    * Indicates the parent has approved the given treatment/test
    */
   APPROVED("אושר"),

   /**
    * Indication for classes indicating that some of the students have approved and others havent
    */
   PARTIAL("חלקית אושר");

   private val imageName = name.lowercase()

   @Composable
   private fun resource() = painterResource("assets/status/$imageName.png")

   @Composable

   fun image() = Image(resource(), description, contentScale = ContentScale.Fit, modifier = Modifier.size(20.dp))

   companion object {
      @Composable
      fun Boolean.status() = (if (this) APPROVED else DENIED)
   }
}

