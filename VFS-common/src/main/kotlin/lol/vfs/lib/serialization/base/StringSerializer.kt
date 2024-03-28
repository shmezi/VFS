package lol.vfs.lib.serialization.base

import lol.vfs.lib.serialization.serializer.Serializer
import org.bson.BsonString
import org.bson.BsonValue

class StringSerializer : Serializer<String> {
   override fun serialize(item: Any): BsonValue = BsonString(item .toString())
   
   override fun deserialize(value: BsonValue): String = value.asString().value
}