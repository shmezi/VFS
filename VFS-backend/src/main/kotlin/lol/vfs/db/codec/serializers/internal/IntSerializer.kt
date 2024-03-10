package lol.vfs.db.codec.serializers.internal

import lol.vfs.db.codec.serializers.Serializer
import org.bson.BSONObject
import org.bson.BsonDocument
import org.bson.BsonInt32
import org.bson.BsonValue


class IntSerializer : Serializer<Int> {

   override fun serialize(value: Int) = BsonInt32(value)

   override fun deserialize(serialized: BsonValue) = serialized.asInt32().value

}