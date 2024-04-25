package lol.vfs.model.users

import kotlinx.serialization.Serializable

/**
 * The type of a user that can log into a system
 */
@Serializable
enum class UserType {
   /**
    * An admin user
    */
   ADMIN,

   /**
    * A parent user
    */
   PARENT,

   /**
    * A doctor user
    */
   DOCTOR;

   /**
    * A pretty print version of the name
    */
   fun prettyPrint() = name[0].uppercase() + image().substring(1)

   /**
    * The image name of the user type
    */
   fun image() = name.lowercase()
}