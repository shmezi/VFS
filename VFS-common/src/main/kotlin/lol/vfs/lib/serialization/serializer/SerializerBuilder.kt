package lol.vfs.lib.serialization.serializer

import org.bson.BsonValue

class SerializerBuilder<T : Any> {
   internal var serializer: ((item: Any) -> BsonValue)? = null
   internal var deserializer: ((value: BsonValue) -> T)? = null

   fun SerializerBuilder<T>.serialize(serializer: (item: Any) -> BsonValue) {
      this.serializer = serializer
   }

   fun SerializerBuilder<T>.deserialize(deserializer: (value: BsonValue) -> T) {
      this.deserializer = deserializer
   }
}