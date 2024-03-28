package lol.vfs.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val id: String, val password: String)
