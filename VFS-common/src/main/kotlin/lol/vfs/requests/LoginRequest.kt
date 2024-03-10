package lol.vfs.requests

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class LoginRequest(val username: String, val password: String)
