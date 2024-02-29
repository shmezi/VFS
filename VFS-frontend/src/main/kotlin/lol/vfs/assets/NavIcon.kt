package lol.vfs.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Status icons for the status of various tests
 */
enum class NavIcon(private val image: String, val description: String) {

   HOME("home", "Home page"),
   EDU("education", "Education center"),
   SCHEDULE("calender", "Schedule");

   @Composable
   fun r() = painterResource("assets/navigation/$image.png")

   @Composable
   fun i() = Image(r(), description, contentScale = ContentScale.Fit, modifier = Modifier.size(20.dp))
}

