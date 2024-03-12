package lol.vfs.serialization.base

import lol.vfs.serialization.Serializer
import org.bson.BsonInt32
import org.bson.BsonValue

class IntSerializer : Serializer<Int> {
   override val reference: Boolean = false
   override fun serialize(item: Any): BsonValue = BsonInt32(item as Int)

   override fun deserialize(value: BsonValue) = value.asInt32().value
}