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
 */
enum class Status(private val image: String, val description: String) {
   /**
    * Indicating that a test has been done / vaccine given etc.
    */
   DONE("done", "מסוים"),

   /**
    * Indicates the parent has not responded / denied the given treatment/test
    */
   DENIED("denied", "לא מאושר"),

   /**
    * Indicates the parent has approved the given treatment/test
    */
   APPROVED("approved", "מאושר"),

   /**
    * Indication for classes indicating that some of the students have approved and others havent
    */
   PARTIAL("partial", "חלקית מאושר");

   @Composable
   fun r() = painterResource("assets/status/$image.png")

   @Composable
   fun i() = Image(r(), description, contentScale = ContentScale.Fit, modifier = Modifier.size(20.dp))

   companion object {
      @Composable
      fun Boolean.status() = (if (this) Status.APPROVED else Status.DENIED)
   }
}

