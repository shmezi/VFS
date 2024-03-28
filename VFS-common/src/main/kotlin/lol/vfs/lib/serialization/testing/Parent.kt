package lol.vfs.lib.serialization.testing

import lol.vfs.lib.db.Id

class Parent(
   @Id
   val id: String,
   val name: String,
//   @DbRef("kids")
   val kid: Kid,
   val k2: Kid
) {
   override fun toString(): String {
      return "$id $name $kid $k2"
   }
}