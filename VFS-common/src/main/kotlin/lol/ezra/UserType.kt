package lol.ezra

import kotlinx.serialization.Serializable
@Serializable
enum class UserType(val prettyPrint:String) {
   ADMIN("Admin"),
   PARENT("Parent"),
   DOCTOR("Doctor")
}