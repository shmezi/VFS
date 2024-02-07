package lol.ezra

import kotlinx.serialization.SerialName

data class Parent(
   @SerialName("_id")
   val id: String,
   val kids: MutableSet<String>
) {

}