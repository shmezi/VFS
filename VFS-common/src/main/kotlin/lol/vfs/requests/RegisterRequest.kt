package lol.vfs.requests

import kotlinx.serialization.Serializable
import lol.vfs.model.users.UserType

@Serializable
data class RegisterRequest(
   val id: String,
   val name: String,
   val lastName: String,
   val image: String,
   val type: UserType,
   val password: String
)
