package lol.vfs.requests

import kotlinx.serialization.Serializable
import lol.vfs.model.users.User
import lol.vfs.model.users.UserType

/**
 * A public representation of the [User] class.
 * It can be transferred between frontend and back.
 * @param id ID of the user.
 * @param name First name of the user.
 * @param lastName Last name of the user.
 * @param type The [UserType] of the user
 */
@Serializable
data class UserRequest(
   val id: String,
   val name: String,
   val lastName: String,
   val type: UserType
)