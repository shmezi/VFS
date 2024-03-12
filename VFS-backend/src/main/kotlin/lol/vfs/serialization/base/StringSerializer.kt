package lol.vfs.serialization.base

import lol.vfs.serialization.Serializer
import org.bson.BsonString
import org.bson.BsonValue

class StringSerializer : Serializer<String> {
   override val reference: Boolean = false
   override fun serialize(item: Any): BsonValue = BsonString(item as String)

   override fun deserialize(value: BsonValue): String = value.asString().value
}