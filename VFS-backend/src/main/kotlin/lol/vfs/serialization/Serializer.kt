package lol.vfs.serialization

import org.bson.BSONObject
import org.bson.BsonValue

interface Serializer<T : Any> {
   val reference: Boolean
   fun serialize(item: Any): BsonValue

   fun deserialize(value: BsonValue): T
}