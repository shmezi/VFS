package lol.vfs


import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
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
import lol.vfs.model.users.Admin
import lol.vfs.model.users.Doctor
import lol.vfs.model.users.Parent
import lol.vfs.model.users.UserType
import lol.vfs.requests.RegisterRequest
import lol.vfs.routing.auth
import lol.vfs.routing.classroom


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

      Medical("MMRV", MedicalType.TREATMENT, Age.FIRST).register()

      Medical("Tdap+IPV", MedicalType.TREATMENT, Age.SECOND).register()

      Medical("Infl.LAIV", MedicalType.TREATMENT, Age.SECOND).register()
      Medical("Infl.LAIV", MedicalType.TREATMENT, Age.THIRD).register()
      Medical("HPV", MedicalType.TREATMENT, Age.SEVENTH).register()
      Medical("Tdap", MedicalType.TREATMENT, Age.EIGHTH).register()


      Medical("שמיעה", MedicalType.TEST, Age.FIRST).register()
      Medical("ראייה", MedicalType.TEST, Age.FIRST).register()

      Medical("אומדן גדילה", MedicalType.TEST, Age.FIRST).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.FIRST).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.SECOND).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.THIRD).register()

      Medical("בדיקת שיניים", MedicalType.TEST, Age.FORTH).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.FIFTH).register()

      Medical("בדיקת שיניים", MedicalType.TEST, Age.SIXTH).register()

      Medical("אומדן גדילה", MedicalType.TEST, Age.SEVENTH).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.SEVENTH).register()

      Medical("ראייה", MedicalType.TEST, Age.EIGHTH).register()
      Medical("בדיקת שיניים", MedicalType.TEST, Age.EIGHTH).register()




      for (c in 2017 .. 2024) {
         assignCurrentYear(getGrade(c) ?: continue)
      }


      RegisterRequest("1", "עזרא", "גולומבק", UserType.PARENT, "123456789").register()

      Database.parentDB.replace("1", Parent("1", mutableSetOf("273559963", "877188817", "753463327")))

      RegisterRequest("2", "אריק", "גולומבק", UserType.ADMIN, "123456789").register()
      Database.adminDB.replace("2", Admin("2", (2017 .. 2024).toSortedSet()))


      RegisterRequest("3", "עדינה", "גולומבק", UserType.DOCTOR, "123456789").register()
      Database.doctorDB.replace("3", Doctor("3", (2017 .. 2024).toSortedSet()))


   }
}

