package lol.vfs.requests

import kotlinx.serialization.Serializable
import lol.vfs.db.users.UserType

@Serializable
data class UserRequest(
   val id: String,
   val name: String,
   val lastName: String,
   val type: UserType
)