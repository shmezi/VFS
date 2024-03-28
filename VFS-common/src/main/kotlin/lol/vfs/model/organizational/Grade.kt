package lol.vfs.model.organizational

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lol.vfs.lib.Date
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Grade(
   @BsonId
   val id: String,
   var startYear: Int,
   var prettyPrint: String,
   var classes: MutableSet<Class>
){
   fun getAge() = Date
}