package lol.vfs.model.users

import kotlinx.serialization.Serializable

@Serializable
enum class UserType(val prettyPrint: String,val image:String) {
   ADMIN("Admin","admin"),
   PARENT("Parent","parent"),
   DOCTOR("Doctor","doctor")
}