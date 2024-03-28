package lol.vfs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StudyData(
   @SerialName("_id")
   val id: String,
   val topic: String,
   val shortDescription: String,
   val description: String,
   val image: String,
   ){

}