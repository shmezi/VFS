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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import lol.vfs.LocalCache
import lol.vfs.LocalCache.getStudents
import lol.vfs.assets.Status
import lol.vfs.assets.Status.Companion.status
import lol.vfs.lib.textOrNull
import lol.vfs.model.medical.learning.LearningMaterial
import lol.vfs.model.organizational.Class
import lol.vfs.model.organizational.Grade
import lol.vfs.model.medical.TestData
import lol.vfs.model.medical.TreatmentData
import lol.vfs.model.users.Student
import lol.vfs.model.users.UserType
import lol.vfs.pages.components.layout.table.TRow
import lol.vfs.styling


fun List<Status>.status(): Status {
   if (isEmpty()) return Status.APPROVED
   if (!contains(Status.PARTIAL) && !contains(Status.DENIED) && !contains(Status.APPROVED)) return Status.DONE
   if (!contains(Status.DENIED) && !contains(Status.PARTIAL)) return Status.APPROVED
   if (!contains(Status.PARTIAL) && !contains(Status.APPROVED) && !contains(Status.DONE)) return Status.DENIED
   return Status.PARTIAL
}

fun TestData.status(): Status {
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
fun LearningMaterial.studyResource() = painterResource("assets/learning/$image")

@Composable
fun LearningMaterial.studyImage(modifier: Modifier = Modifier) =
   Image(studyResource(), description, contentScale = ContentScale.FillBounds, modifier = modifier)

//Student extensions
@Composable
fun Student.treatmentStatus() = treatments.values.map { it.status() }.status()

@Composable
fun Student.testStatus() = tests.values.map { it.status() }.status()

@Composable
fun Student?.rowifyApproval(status: Boolean): Array<TRow> {

   val rows = mutableListOf<TRow>()
   val s = this ?: return rows.toTypedArray()

   for (test in tests) {
      rows.add(
         TRow(
            {
               var approved by mutableStateOf(test.value.approved)
               if (status) test.value.status().image()
               else Checkbox(approved, {

                  test.value.approved = !test.value.approved
                  approved = test.value.approved
                  runBlocking {
                     LocalCache.postToCloud(s.id)
                  }
               }
               )
            },
            { Text(style = styling, overflow = TextOverflow.Ellipsis, text = test.key) },
            { Text(style = styling, overflow = TextOverflow.Ellipsis, text = name) },
            {
               Text(
                  style = styling, overflow = TextOverflow.Ellipsis, text =
                  runBlocking {
                     LocalCache.getGrade(s.grade).medicals[test.key]
                  }.toString()
               )


            })
      )
   }
   for (treatment in treatments) {
      rows.add(
         TRow(
            {
               var approved by mutableStateOf(treatment.value.approved)

               if (status) treatment.value.status().image()
               else Checkbox(approved, {
                  treatment.value.approved = !treatment.value.approved
                  approved = treatment.value.approved
                  runBlocking {
                     LocalCache.postToCloud(s.id)
                  }
               })
            },
            { Text(style = styling, overflow = TextOverflow.Ellipsis, text = treatment.key) },
            { Text(style = styling, overflow = TextOverflow.Ellipsis, text = name) },
            {
               Text(style = styling, overflow = TextOverflow.Ellipsis, text =
               runBlocking {
                  LocalCache.getGrade(s.grade).medicals[treatment.key]
               }.toString()
               )
            })
      )
   }

   return rows.toTypedArray()
}


@Composable
fun Student?.rowifyDocTests(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   val s = this ?: return rows.toTypedArray()

   for (t in tests) {
      var complete by mutableStateOf(t.value.isComplete())

      rows.add(TRow(
         {
            var u by remember { mutableStateOf(t.value.recommendations ?: "") }

            TextField(u, {
               u = it
               t.value.recommendations = it.textOrNull()
               runBlocking { LocalCache.postToCloud(s.id) }
               complete = (t.value.results != null || t.value.recommendations != null)

            }, enabled = t.value.approved, label = {
               Text(style = styling, overflow = TextOverflow.Ellipsis, text = "המלצה")
            })
         },
         {
            var text by remember { mutableStateOf(t.value.results ?: "") }

            TextField(
               value = text,
               onValueChange = {
                  text = it
                  t.value.results = it.textOrNull()
                  complete = (t.value.results != null || t.value.recommendations != null)
                  runBlocking { LocalCache.postToCloud(s.id) }
               },
               enabled = t.value.approved,
               label = { Text(style = styling, overflow = TextOverflow.Ellipsis, text = "תוצאה") })
         },
         {
            Checkbox(complete, {
               if (t.value.results.textOrNull() == null && t.value.recommendations.textOrNull() == null) {
                  complete = !complete
                  if (complete) {
                     t.value.results = ""
                  } else t.value.results = null
                  runBlocking { LocalCache.postToCloud(s.id) }
               }

            }, enabled = t.value.approved)
         },
         { t.value.approved.status().image() },
         { Text(style = styling, overflow = TextOverflow.Ellipsis, text = t.key) }

      ))
   }


   return rows.toTypedArray()
}

@Composable
fun Student?.rowifyDocTreatments(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   val s = this ?: return rows.toTypedArray()

   for (t in treatments) {
      var complete by mutableStateOf(t.value.isComplete())

      rows.add(TRow(
         {
            var u by remember { mutableStateOf(t.value.afterEffects ?: "") }
            TextField(u, {
               u = it
               t.value.afterEffects = it.textOrNull()
               complete = t.value.afterEffects != null
               runBlocking { LocalCache.postToCloud(s.id) }
            }, enabled = t.value.approved, label = {
               Text(style = styling, overflow = TextOverflow.Ellipsis, text = "תופאות לוואי")
            })
         },
         {
            Checkbox(complete, {

               if (t.value.afterEffects.textOrNull() == null) {
                  complete = !complete
                  if (complete) {
                     t.value.afterEffects = ""
                  } else t.value.afterEffects = null
                  runBlocking { LocalCache.postToCloud(s.id) }

               }
            }, enabled = t.value.approved)
         },
         { t.value.approved.status().image() },
         { Text(style = styling, overflow = TextOverflow.Ellipsis, text = t.key) }

      ))
   }


   return rows.toTypedArray()
}

@Composable
fun Student?.rowifyTests(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   val s = this ?: return rows.toTypedArray()
   for (test in tests) {
      val v = test.value
      rows.add(TRow(v.recommendations, v.results, runBlocking {
         LocalCache.getGrade(s.grade).medicals[test.key]
      }.toString(), test.key))
   }
   return rows.toTypedArray()
}

@Composable
fun Student?.rowifyTreatments(): Array<TRow> {
   val rows = mutableListOf<TRow>()
   val s = this ?: return rows.toTypedArray()
   for (treatment in treatments) {
      val v = treatment.value
      rows.add(TRow(v.afterEffects, runBlocking {
         LocalCache.getGrade(s.grade).medicals[treatment.key]
      }.toString(), treatment.key))
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
@Composable
fun Class.testStatus() = getStudents(students).map { it.testStatus() }.status()

@Composable
fun Class.treatmentStatus() = getStudents(students).map { it.treatmentStatus() }.status()

//Grade extensions
@Composable
fun Grade.testStatus() = classes.values.map { it.testStatus() }.status()

@Composable
fun Grade.treatmentStatus() = classes.values.map { it.treatmentStatus() }.status()


@Composable
fun UserType.studyResource() = painterResource("assets/user/${image()}.png")

@Composable
fun UserType.studyImage() =
   Image(studyResource(), image(), contentScale = ContentScale.Fit, modifier = Modifier.size(45.dp))
