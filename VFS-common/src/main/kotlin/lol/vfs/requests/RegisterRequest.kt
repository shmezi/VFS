package lol.vfs.requests

import kotlinx.serialization.Serializable
import lol.vfs.db.users.UserType

@Serializable
data class RegisterRequest(
   val id: String,
   val name: String,
   val lastName: String,
   val image: String,
   val type: Set<UserType>,
   val password: String
)
