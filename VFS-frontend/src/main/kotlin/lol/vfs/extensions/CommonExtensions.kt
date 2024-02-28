package lol.vfs.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.db.Class
import lol.vfs.db.Grade
import lol.vfs.db.Student
import lol.vfs.db.UserType

fun List<Boolean>.bstatus(): Status {
   var status = Status.DENIED
   for (test in this) {
      if (status == Status.DENIED && test) {
         status = Status.PARTIAL
         continue
      }
      if (status == Status.PARTIAL && !test)
         return Status.PARTIAL
   }
   if (status != Status.DENIED)
      return Status.APPROVED
   return status
}

fun Student.status() = medicalTests.values.map { it.approved }.bstatus()


fun List<Status>.sstatus(): Status {
   var status = Status.DENIED
   for (s in this) {
      if (status == Status.DENIED && (s == Status.APPROVED || s == Status.PARTIAL)) {
         status = Status.PARTIAL
         continue
      }
      if (status == Status.PARTIAL && s == Status.DENIED) return Status.PARTIAL
   }
   if (status == Status.PARTIAL) return Status.APPROVED
   return status
}

fun Class.status() = this.students.map { it.status() }.sstatus()

fun Grade.status() = this.classes.map { it.status() }.sstatus()

@Composable
fun UserType.r() = painterResource("assets/user/$image.png")

@Composable
fun UserType.i() = Image(r(), image, contentScale = ContentScale.Fit, modifier = Modifier.size(45.dp))
