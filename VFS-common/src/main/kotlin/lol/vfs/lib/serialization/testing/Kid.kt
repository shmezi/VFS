package lol.vfs.lib.serialization.testing

import lol.vfs.lib.db.Id

class Kid(@Id val id: String, val name: String, val lastname: String) {
   override fun toString(): String {
      return "$id $name $lastname"
   }
}