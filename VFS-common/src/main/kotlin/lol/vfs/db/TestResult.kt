package lol.vfs.db

import kotlinx.serialization.Serializable

@Serializable
data class TestResult(
   var approved: Boolean = false,
   var result: String? = null
) {

}