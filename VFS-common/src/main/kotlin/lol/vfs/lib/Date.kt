package lol.vfs.lib

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date

@Serializable
data class Date(var year: Int, var month: Int, var day: Int) {
   override fun toString() = "$year/$month/$day"

   companion object {
      /**
       * Returns the current year we are in
       *
       */
      fun today() = SimpleDateFormat("yyyy").format(Date()).toInt()
   }
}

