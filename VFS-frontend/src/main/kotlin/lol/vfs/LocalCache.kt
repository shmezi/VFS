package lol.vfs

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.lib.printing.pq
import lol.vfs.model.medical.Medical
import lol.vfs.model.medical.learning.LearningMaterial
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.*
import kotlin.collections.Collection
import kotlin.collections.Set
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.collections.set
import kotlin.collections.setOf

object LocalCache {
   private val students = mutableMapOf<String, Student>()
   private val grades = mutableMapOf<Int, Grade>()

   val studyData = runBlocking {
      client.get("studyData".url()).body<Set<LearningMaterial>>()
   }


   suspend fun cacheStudent(id: String): Student {
      val student = client.get("student".url()) {
         this.setBody(id)
      }.body<Student>()
      students[id] = student

      return student
   }

   suspend fun postToCloud(student: String) {
      client.post("update/student".url()) {
         contentType(ContentType.Application.Json)
         setBody(students[student] ?: return)


      }
   }


   suspend fun getStudent(id: String): Student {
      return students[id] ?: cacheStudent(id)
   }

   fun getStudents(collection: Collection<String>) = runBlocking {
      val students = mutableSetOf<Student>()
      for (c in collection)
         students.add(getStudent(c))
      students
   }

   suspend fun cacheGrade(id: Int): Grade {
      val grade = client.get("grade".url()) {
         setBody(id)
         contentType(ContentType.Application.Json)
      }.body<Grade>()
      grades[id] = grade
      return grade
   }

   suspend fun getGrade(id: Int): Grade {
      return grades[id] ?: cacheGrade(id)
   }

   fun date(grade: Grade, medical: Medical) {

   }

   fun getGrades(collection: Collection<Int>) = runBlocking {
      val grades = mutableSetOf<Grade>()
      for (c in collection)
         grades.add(getGrade(c))
      grades //Return value
   }


   suspend fun getGradeIds(): Set<Int> {
      val user = getUser()
      return when (user.type) {
         UserType.DOCTOR -> client.get("doctor".url()) { setBody(user.id) }.body<Doctor>().grades
         UserType.ADMIN -> client.get("admin".url()) { setBody(user.id) }.body<Admin>().grades
         else -> setOf()
      }
   }

   suspend fun getStudentIds(): Set<String> {
      val user = getUser()
      if (user.type != UserType.PARENT) return setOf()
      val parent = client.get("parent".url()) {
         setBody(user.id)
      }.body<Parent>().pq()
      return parent.kids
   }
}