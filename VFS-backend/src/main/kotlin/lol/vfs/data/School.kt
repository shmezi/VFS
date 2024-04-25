package lol.vfs.data

import lol.vfs.lib.printing.pq
import lol.vfs.model.medical.Medical
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

   fun studyData(): Set<LearningMaterial> = LearnCenterData.entries.map { it.learningMaterial }.toSet()
   suspend fun assignCurrentYear(vararg grades: Grade) {
      for (grade in grades) {
         val all = Database.medicalDb.getAll().pq("ALL!")
         val medicals = all.filter { it.grade == grade.getAge() }
         for (med in medicals) {

         }
      }
   }


   suspend fun Medical.register() {
      Database.medicalDb.replace(this.name, this)

   }
}

class S{

}