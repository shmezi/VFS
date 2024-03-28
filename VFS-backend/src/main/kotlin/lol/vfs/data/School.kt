package lol.vfs.data

import lol.vfs.model.StudyData
import lol.vfs.model.organizational.Grade
import lol.vfs.model.testing.Medical
import lol.vfs.model.users.Student
import lol.vfs.model.users.Admin
import lol.vfs.model.users.Doctor
import lol.vfs.model.users.Parent

object School {
   suspend fun getGrade(id: String): Grade? = Database.gradeDB.get(id)
   suspend fun getStudent(id: String): Student? = Database.studentDB.get(id)
   suspend fun getAdmin(id: String): Admin? = Database.adminDB.get(id)
   suspend fun getDoctor(id: String): Doctor? = Database.doctorDB.get(id)
   suspend fun getParent(id: String): Parent? = Database.parentDB.get(id)

//   suspend fun studyData(id: String): StudyData? = Database.testDB.getOrDefault(id) {}

}

