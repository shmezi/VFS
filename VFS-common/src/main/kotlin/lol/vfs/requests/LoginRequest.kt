package lol.vfs.requests

import kotlinx.serialization.Serializable

/**
 * Represents a user's login request
 * @param id The attempted id used to log in with.
 * @param password The attempted password used to log in with.
 */
@Serializable
data class LoginRequest(
   val id: String,
   val password: String
)
