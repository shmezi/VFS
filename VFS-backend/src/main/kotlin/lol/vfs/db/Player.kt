package lol.vfs.db

import kotlinx.serialization.Serializable


class Player(
   override val id: String,
//   @DbRef
   val bas: Son,
   val name: String
) : DBItem<String>() {
}