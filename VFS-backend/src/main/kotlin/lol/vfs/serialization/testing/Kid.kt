package lol.vfs.serialization.testing

class Kid(val id: String, val name: String, val lastname: String) {
   override fun toString(): String {
      return "$id $name $lastname"
   }
}