package lol.vfs.lib

fun String?.textOrNull(): String? {
   if (this == "" || this == null)
      return null
   return this
}