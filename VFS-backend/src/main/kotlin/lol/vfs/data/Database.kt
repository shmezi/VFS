package lol.vfs.data

import lol.vfs.lib.db.CachedCollection
import lol.vfs.lib.db.Database
import lol.vfs.model.StudyData
import lol.vfs.model.organizational.Age
import lol.vfs.model.organizational.Grade
import lol.vfs.model.testing.Medical
import lol.vfs.model.testing.MedicalType
import lol.vfs.model.users.*


object Database {
   lateinit var userDB: CachedCollection<User, String>
   lateinit var gradeDB: CachedCollection<Grade, String>

   lateinit var adminDB: CachedCollection<Admin, String>
   lateinit var doctorDB: CachedCollection<Doctor, String>
   lateinit var parentDB: CachedCollection<Parent, String>
   lateinit var studentDB: CachedCollection<Student, String>
   lateinit var testDB: CachedCollection<Medical, String>
   lateinit var studyData: CachedCollection<StudyData, String>

   init {
      Database {
         userDB = collection(User::class.java, "user") {
            default {
               User(it, "", "", "", "", mutableListOf(), UserType.PARENT)
            }
         }
         gradeDB = collection(Grade::class.java, "grade") {
            default {
               Grade(it, 2020, "", mutableSetOf())
            }
         }

         adminDB = collection(Admin::class.java, "admin") {
            default {
               Admin(it, mutableSetOf())
            }
         }
         doctorDB = collection(Doctor::class.java, "doctor") {
            default {
               Doctor(it, mutableSetOf())
            }
         }

         parentDB = collection(Parent::class.java, "parent") {
            default {
               Parent(it, mutableSetOf())
            }
         }

         studentDB = collection(Student::class.java, "student") {
            default {
               Student(it, "", "", "", "", mutableMapOf(), mutableMapOf())
            }
         }
         testDB = collection(Medical::class.java, "test") {
            default {
               Medical(it, "", MedicalType.TEST, Age.FIFTH)
            }
         }
         studyData = collection(StudyData::class.java, "studyData") {
            default {
               StudyData(it, "", "", "", "")
            }
         }

      }
   }
}

