package lol.vfs.db.codec.serializers.internal

import lol.vfs.db.codec.serializers.Serializer
import org.bson.BsonString
import org.bson.BsonValue

class StringSerializer : Serializer<String> {
   override fun deserialize(serialized: BsonValue): String = serialized.asString().value

   override fun serialize(value: String): BsonValue = BsonString("S$value")
}