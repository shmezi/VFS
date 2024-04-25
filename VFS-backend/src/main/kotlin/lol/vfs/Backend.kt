package lol.vfs


import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import lol.vfs.data.Database
import lol.vfs.data.School.assignCurrentYear
import lol.vfs.data.School.getGrade
import lol.vfs.data.School.register
import lol.vfs.data.UserAuth.register
import lol.vfs.model.medical.Medical
import lol.vfs.model.medical.MedicalType
import lol.vfs.model.organizational.Age
import lol.vfs.model.users.UserType
import lol.vfs.requests.RegisterRequest
import lol.vfs.routing.auth
import lol.vfs.routing.classroom
import java.io.File


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
   runBlocking {
       Medical("testing123",MedicalType.TEST,Age.FIRST).register()
      Medical("testing1",MedicalType.TEST,Age.SECOND).register()
      assignCurrentYear(getGrade(1)!!)

      if (!Database.studentDB.isEmpty()) return@runBlocking
      Database.importClassroomData(
         File(
            javaClass.classLoader.getResource("students.csv")?.path ?: throw NotFoundException("OOF")
         )
      )
      RegisterRequest("1", "עזרא", "גולומבק", UserType.PARENT, "123456789").register()
      RegisterRequest("2", "אריק", "גולומבק", UserType.ADMIN, "123456789").register()
      RegisterRequest("3", "עדינה", "גולומבק", UserType.DOCTOR, "123456789").register()

   }
}

