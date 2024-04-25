package lol.vfs.requests

import kotlinx.serialization.Serializable
import lol.vfs.model.users.UserType

/**
 * Represents a user's register request
 * @param id The ID provided for the user.
 * @param name The name provided for the user.
 * @param lastName The lastname provided for the user.
 * @param type The [UserType] of the provided for the user.
 * @param password The password provided for the user.
 */
@Serializable
data class RegisterRequest(
   val id: String,
   val name: String,
   val lastName: String,
   val type: UserType,
   val password: String
)
