package lol.vfs.minilib

import kotlinx.serialization.Serializable

@Serializable
data class Date(var year: Int, var month: Int, var day: Int) {
   override fun toString() = "$year/$month/$day"
}
