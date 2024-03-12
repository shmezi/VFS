package lol.vfs.serialization.testing

import lol.vfs.serialization.DbRef

class Parent(val _id: String, val name: String, @DbRef("kids") val kid: Kid, val k2: Kid) {
   override fun toString(): String {
      return "$_id $name $kid $k2"
   }
}