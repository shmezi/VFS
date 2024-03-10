package lol.vfs.db.codec.serializers

import org.bson.BsonValue

interface Serializer<T : Any> {

   fun deserialize(serialized: BsonValue): T
   fun serialize(value: T): BsonValue
}