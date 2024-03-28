package lol.vfs.lib.serialization.serializer

import org.bson.BsonValue

interface Serializer<T : Any> {

   fun serialize(item: Any): BsonValue

   fun deserialize(value: BsonValue): T

}