package lol.vfs.data

import lol.vfs.lib.db.CachedCollection
import lol.vfs.lib.db.Database
import lol.vfs.model.medical.learning.LearningMaterial
import lol.vfs.model.organizational.Age
import lol.vfs.model.organizational.Class
import lol.vfs.model.organizational.Grade
import lol.vfs.model.medical.Medical
import lol.vfs.model.medical.MedicalType
import lol.vfs.model.users.*
import java.io.File


object Database {


   lateinit var userDB: CachedCollection<User, String>
   lateinit var gradeDB: CachedCollection<Grade, Int>

   lateinit var adminDB: CachedCollection<Admin, String>
   lateinit var doctorDB: CachedCollection<Doctor, String>
   lateinit var parentDB: CachedCollection<Parent, String>
   lateinit var studentDB: CachedCollection<Student, String>
   lateinit var medicalDb: CachedCollection<Medical, String>

   lateinit var learningMaterial: CachedCollection<LearningMaterial, String>


   init {

      Database("VFS") {
         userDB = collection(User::class.java, "user") {
            default {
               User(it, "", "", "", UserType.PARENT, mutableListOf())
            }
         }
         gradeDB = collection(Grade::class.java, "grade") {
            default {
               Grade(it, mutableMapOf())
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
               Student(it, "", "", 0, 0, mutableMapOf(), mutableMapOf())
            }
         }
         medicalDb = collection(Medical::class.java, "medical") {
            default {
               Medical(it, MedicalType.TEST, Age.FIFTH)
            }

         }
         learningMaterial = collection(LearningMaterial::class.java, "studyData") {
            default {
               LearningMaterial(it, "", "", "", "")
            }
         }

      }
   }


}

