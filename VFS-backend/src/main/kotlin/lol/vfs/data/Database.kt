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
               Grade(it, 2020, mutableMapOf())
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
         medicalDb = collection(Medical::class.java, "test") {
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

   suspend fun importClassroomData(file: File) {
      if (file.extension != "csv") throw NotImplementedError("File was not a csv!")
      var x = true
      val students = mutableListOf<Student>()
      val grades = mutableMapOf<Int, Grade>()
      file.forEachLine {
         if (!x) {
            val values = it.split(",")
            fun Int.v() = values[this]
            fun Int.vi() = values[this].toInt()
            //Decoded:
            val id = 0.v()
            val first = 1.v()
            val last = 2.v()
            val grade = 3.vi()
            val clazz = 4.vi()
            val startYear = 5.vi()
            val student = Student(
               id,
               first,
               last,
               grade,
               clazz,
               mutableMapOf(),
               mutableMapOf()
            )
            students.add(student)
            val g = grades.getOrPut(grade) { Grade(grade, startYear, mutableMapOf()) }
            g.classes.getOrPut(clazz.toString()) { Class(clazz, g.id, mutableSetOf()) }.students.add(id)
         } else x = false
      }
      studentDB.insertMany(*students.toTypedArray())

      gradeDB.insertMany(*grades.values.toTypedArray())
   }


}

