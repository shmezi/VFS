package lol.vfs.lib.gimatria

object GimatriaConverter {
   val letters = Letters.bigToSmall()
   fun Int.toGimatria(): String {
      var value = this
      var result = ""
      for (l in letters) {
         while (value >= l.value) {
            value -= l.value
            result += l.letter

         }
      }
      return result
   }
}