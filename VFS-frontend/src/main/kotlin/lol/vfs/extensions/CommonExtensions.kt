package lol.vfs.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.db.StudyData
import lol.vfs.db.organizational.Class
import lol.vfs.db.organizational.Grade
import lol.vfs.db.testing.TestResult
import lol.vfs.db.testing.TreatmentData
import lol.vfs.db.users.Student
import lol.vfs.db.users.UserType
import lol.vfs.pages.components.table.TRow


fun List<Status>.status(): Status {
   if (isEmpty()) return Status.APPROVED
   if (!contains(Status.PARTIAL) && !contains(Status.DENIED) && !contains(Status.APPROVED)) return Status.DONE
   if (!contains(Status.DENIED) && !contains(Status.PARTIAL)) return Status.APPROVED
   if (!contains(Status.PARTIAL) && !contains(Status.APPROVED) && !contains(Status.DONE)) return Status.DENIED
   return Status.PARTIAL
}

fun TestResult.status(): Status {
   return when {
      !approved -> Status.DENIED
      isComplete() -> Status.DONE
      else -> Status.APPROVED
   }
}

fun TreatmentData.status(): Status {
   return when {
      !approved -> Status.DENIED
      isComplete() -> Status.DONE
      else -> Status.APPROVED
   }
}

@Composable
fun StudyData.r() = painterResource("assets/learning/$image")

@Composable
fun StudyData.i(modifier: Modifier = Modifier) =
   Image(r(), description, contentScale = ContentScale.FillBounds, modifier = modifier)

//Student extensions
fun Student.treatmentStatus() = treatments.values.map { it.status() }.status()
fun Student.testStatus() = tests.values.map { it.status() }.status()

@Composable
fun Student?.rowify(status: Boolean): Array<TRow> {

   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()
   for (test in tests) {
      rows.add(TRow({
         if (status)
            test.value.status().i()
         else
             Checkbox(test.value.approved, {
              test.value.approved = !test.value.approved
            })
      }, { Text(test.key) }, { Text(name) }))
   }
   for (treatment in treatments) {
      rows.add(TRow({
         if (status)
            treatment.value.status().i()
         else
            Checkbox(treatment.value.approved, {
              treatment.value.approved = !treatment.value.approved
            })
      }, { Text(treatment.key) }, { Text(name) }))
   }

   return rows.toTypedArray()
}

@Composable
fun RowScope.DColumn(
   weight: Float, modifier: Modifier = Modifier.weight(weight), scope: @Composable ColumnScope.() -> Unit
) = Column(
   modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
   horizontalAlignment = Alignment.CenterHorizontally,
   content = scope
)


//Class extensions

fun Class.testStatus() = students.map { it.testStatus() }.status()
fun Class.treatmentStatus() = students.map { it.treatmentStatus() }.status()
//Grade extensions

fun Grade.testStatus() = classes.map { it.testStatus() }.status()
fun Grade.treatmentStatus() = classes.map { it.treatmentStatus() }.status()


@Composable
fun UserType.r() = painterResource("assets/user/$image.png")

@Composable
fun UserType.i() = Image(r(), image, contentScale = ContentScale.Fit, modifier = Modifier.size(45.dp))
