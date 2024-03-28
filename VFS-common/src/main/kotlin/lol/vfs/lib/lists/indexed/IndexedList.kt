package lol.vfs.lib.lists.indexed

/**
 * WARNING!!! This is a poorly written system that will get very laggy very quickly. it can be written way better.
 *
 */
class IndexedList<E : Indexed> {
   internal val mapping = mutableMapOf<Int, E>()
   private val list = mutableListOf<E>()


   internal fun regenerate() {
      list.clear()
      for (item in mapping.keys.sorted()) {
         list.add(mapping[item] ?: continue)
      }
   }

   fun add(value: E) {
      mapping[value.index] = value
      regenerate()
   }

   fun remove(value: E) {
      mapping.remove(value.index)
      regenerate()
   }

}

fun <E : Indexed> indexedListOf(vararg items: E) =
   IndexedList<E>().apply { items.forEach { mapping[it.index] = it; regenerate() } }

