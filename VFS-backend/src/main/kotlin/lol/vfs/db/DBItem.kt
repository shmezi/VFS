package lol.vfs.db

abstract class DBItem<ID> {
   abstract val id: ID
   internal open var changed: Boolean = false
   fun updated() {
      changed = true
   }
}