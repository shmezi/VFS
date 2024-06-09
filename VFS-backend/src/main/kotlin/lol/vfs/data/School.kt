package lol.vfs.data

import lol.vfs.lib.Date
import lol.vfs.model.medical.Medical
import lol.vfs.model.medical.MedicalType
import lol.vfs.model.medical.TestData
import lol.vfs.model.medical.TreatmentData
import lol.vfs.model.medical.learning.LearningMaterial
import lol.vfs.model.organizational.Grade
import lol.vfs.model.users.Admin
import lol.vfs.model.users.Doctor
import lol.vfs.model.users.Parent
import lol.vfs.model.users.Student
import lol.vfs.utils.LearnCenterData

object School {

   suspend fun getGrade(id: Int): Grade? = Database.gradeDB.get(id)
   suspend fun getStudent(id: String): Student? = Database.studentDB.get(id)
   suspend fun getAdmin(id: String): Admin? = Database.adminDB.get(id)
   suspend fun getDoctor(id: String): Doctor? = Database.doctorDB.get(id)
   suspend fun getParent(id: String): Parent? = Database.parentDB.get(id)

   suspend fun updateStudent(student: Student): Boolean {
      Database.studentDB.replace(student.id, student)
      return true
   }

   suspend fun updateGrade(grade: Grade): Boolean {
      Database.gradeDB.replace(grade.id, grade)
      return true
   }

   fun studyData(): Set<LearningMaterial> = LearnCenterData.entries.map { it.learningMaterial }.toSet()

   fun s() {
   }

   suspend fun assignCurrentYear(vararg grades: Grade) {
      val all = Database.medicalDb.getAll()
      for (grade in grades) {
         val medicals = all.filter { it.grade == grade.getAge() }
         val students = grade.getStudents().mapNotNull { getStudent(it) }
         for (medical in medicals) {
            val date = medical.defaultDate
            grade.medicals[medical.name] = date
            if (medical.type == MedicalType.TEST) {
               for (student in students) {
                  student.tests[medical.name] = TestData(medical.name)
               }
               continue
            }
            for (student in students) {
               student.treatments[medical.name] = TreatmentData(medical.name)
            }
         }
         for (s in students) {
            updateStudent(s)
         }
         updateGrade(grade)
      }

   }


   suspend fun Medical.register() {
      Database.medicalDb.replace(id, this)
   }
}

