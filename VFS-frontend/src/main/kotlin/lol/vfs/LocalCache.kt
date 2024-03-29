package lol.vfs

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import lol.vfs.lib.printing.pq
import lol.vfs.model.StudyData
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.*
import lol.vfs.requests.LoginRequest
import kotlin.collections.Collection
import kotlin.collections.Set
import kotlin.collections.forEach
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.collections.set
import kotlin.collections.setOf

object LocalCache {
   private val students = mutableMapOf<String, Student>()
   private val grades = mutableMapOf<String, Grade>()
   val studentGrade = mutableMapOf<String, String>()
   val studentClazz = mutableMapOf<String, String>()
   val studyData = runBlocking {
      client.get("studyData".url()) .body<Set<StudyData>>()
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

   suspend fun cacheGrade(id: String): Grade {
      val grade = client.get("grade".url()) {
         setBody(id)

      }.body<Grade>()
      grade.classes.forEach { clazz ->
         clazz.students.forEach {
            studentGrade[it] = grade.id
            studentClazz[it] = clazz.id
         }
      }
      grades[id] = grade
      return grade
   }

   suspend fun getGrade(id: String): Grade {
      return grades[id] ?: cacheGrade(id)
   }

   fun getGrades(collection: Collection<String>) =
      runBlocking {
         val grades = mutableSetOf<Grade>()
         for (c in collection)
            grades.add(getGrade(c))
         grades.pq("Grades")
      }


   suspend fun getGradeIds(): Set<String> {
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