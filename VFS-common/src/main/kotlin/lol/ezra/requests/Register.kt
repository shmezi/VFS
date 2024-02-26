package lol.ezra.requests

import kotlinx.serialization.Serializable
import lol.ezra.UserType

@Serializable
data class Register(
   val id: String,
   val name: String,
   val lastName: String,
   val image: String,
   val type: Set<UserType>,
   val password: String
)
