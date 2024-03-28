package lol.vfs.lib.serialization.base

import lol.vfs.lib.serialization.serializer.Serializer
import org.bson.BsonInt32
import org.bson.BsonValue

class IntSerializer : Serializer<Int> {
   override fun serialize(item: Any): BsonValue = BsonInt32(item as Int)

   override fun deserialize(value: BsonValue) = value.asInt32().value
}