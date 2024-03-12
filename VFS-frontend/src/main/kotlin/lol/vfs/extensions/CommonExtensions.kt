package lol.vfs.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lol.vfs.assets.Status
import lol.vfs.assets.Status.Companion.status
import lol.vfs.model.StudyData
import lol.vfs.model.organizational.Class
import lol.vfs.model.organizational.Grade
import lol.vfs.model.testing.TestResult
import lol.vfs.model.testing.TreatmentData
import lol.vfs.model.users.Student
import lol.vfs.model.users.UserType
import lol.vfs.pages.components.layout.table.TRow


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
fun Student?.rowifyApproval(status: Boolean): Array<TRow> {

   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()
   for (test in tests) {
      rows.add(TRow({
         if (status) test.value.status().i()
         else Checkbox(test.value.approved, {
//              test.value.approved = !test.value.approved
         })
      }, { Text(test.key) }, { Text(name) }, { Text(test.value.date.toString()) }))
   }
   for (treatment in treatments) {
      rows.add(TRow({
         if (status) treatment.value.status().i()
         else Checkbox(treatment.value.approved, {
//              treatment.value.approved = !treatment.value.approved
         })
      }, { Text(treatment.key) }, { Text(name) }, { Text(treatment.value.date.toString()) }))
   }

   return rows.toTypedArray()
}


@Composable
fun Student?.rowifyDocTests(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()

   for (t in tests) {
      rows.add(TRow(
         {
            var u by remember { mutableStateOf(t.value.recommendations ?: "") }
            TextField(u, {
               u = it
            }, label = {
               Text("המלצה")
            })
         },
         {
            var text by remember { mutableStateOf(t.value.results ?: "") }

            TextField(
               value = text,
               onValueChange = { text = it },
               label = { Text("תוצאה") }
            )
         },
         {
            Checkbox(t.value.isComplete(), { })
         },
         { t.value.approved.status().i() },
         { Text(t.key) }

      )
      )
   }


   return rows.toTypedArray()
}

@Composable
fun Student?.rowifyDocTreatments(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()

   for (t in treatments) {
      rows.add(TRow(
         {
            var u by remember { mutableStateOf(t.value.afterEffects ?: "") }
            TextField(u, {
               u = it
            }, label = {
               Text("תופאות לוואי")
            })
         },
         {
            Checkbox(t.value.isComplete(), { })
         },
         { t.value.approved.status().i() },
         { Text(t.key) }

      )
      )
   }


   return rows.toTypedArray()
}

@Composable
fun Student?.rowifyTests(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()
   for (test in tests) {
      val v = test.value
      rows.add(TRow(v.recommendations, v.results, v.date.toString(), test.key))
   }
   return rows.toTypedArray()
}

@Composable

fun Student?.rowifyTreatments(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   this ?: return rows.toTypedArray()
   for (treatment in treatments) {
      val v = treatment.value
      rows.add(TRow(v.afterEffects, v.date.toString(), treatment.key))
   }
   return rows.toTypedArray()
}

/**
 * Default column with weight
 */
@Composable
fun RowScope.DColumn(
   weight: Float, modifier: Modifier = Modifier.weight(weight), scope: @Composable ColumnScope.() -> Unit
) = Column(
   modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
   horizontalAlignment = Alignment.CenterHorizontally,
   content = scope
)

/**
 * Default column with weight
 */
@Composable
fun ColumnScope.DColumn(
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
