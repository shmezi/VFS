package lol.vfs


import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.css.tr
import lol.vfs.data.Database
import lol.vfs.routing.auth
import lol.vfs.routing.classroom
import lol.vfs.utils.SDS
import javax.xml.crypto.Data


fun main(args: Array<String>) = EngineMain.main(args)
lateinit var application: Application
fun Application.module() {
   application = this
   install(ContentNegotiation) {
      json()
   }
   install(Routing)
   auth()
   classroom()
   if (false) runBlocking {
      SDS.students.forEach {
         Database.studentDB.getOrDefault(it.id) {
            name = it.name
            lastName = it.lastName
            tests = it.tests
            treatments = it.treatments
            clazz = it.clazz
            grade =  it.clazz
         }
      }
      SDS.grades.forEach {
         Database.gradeDB.getOrDefault(it.id) {
            classes = it.classes
            startYear = it.startYear
            prettyPrint = it.prettyPrint
         }
      }

   }
}

