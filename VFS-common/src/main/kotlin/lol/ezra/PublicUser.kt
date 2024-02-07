package lol.ezra


data class PublicUser(
   val id: String,
   val name: String,
   val lastName: String,
   val image: String,
   val type: Set<UserType>
)