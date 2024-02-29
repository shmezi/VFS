package lol.vfs.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.db.*


fun List<Boolean>.bstatus(): Status {
   if (this.isEmpty()) return Status.APPROVED
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


fun List<Status>.sstatus(): Status {
   if (this.isEmpty()) return Status.APPROVED
   if (!contains(Status.DENIED) && !contains(Status.PARTIAL)) return Status.APPROVED
   if (!contains(Status.PARTIAL) && !contains(Status.APPROVED)) return Status.DENIED
   return Status.PARTIAL
}

fun TestResult.status(): Status {
   return when {
      !approved -> Status.DENIED
      approved && result == null -> Status.APPROVED
      else -> Status.DONE
   }
}

@Composable
fun StudyData.r() = painterResource("assets/learning/$image")

@Composable
fun StudyData.i(modifier: Modifier = Modifier) =
   Image(r(), description, contentScale = ContentScale.FillBounds, modifier = modifier)

fun Student.approved() = medicalTests.values.map { it.approved }.bstatus()

fun Class.approved() = this.students.map { it.approved() }.sstatus()

fun Grade.approved() = this.classes.map { it.approved() }.sstatus()

fun Student.doneTests() = medicalTests.values.map { it.result != null }.bstatus()
fun Class.doneTests() = this.students.map { it.doneTests() }.sstatus()
fun Grade.doneTests() = this.classes.map { it.doneTests() }.sstatus()


@Composable
fun UserType.r() = painterResource("assets/user/$image.png")

@Composable
fun UserType.i() = Image(r(), image, contentScale = ContentScale.Fit, modifier = Modifier.size(45.dp))
