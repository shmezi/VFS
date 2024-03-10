package lol.vfs.db.codec

import lol.vfs.db.codec.serializers.Serializer
import lol.vfs.minilib.pq
import org.bson.BsonValue


open class Codec(val id: String) {

   val serializers = mutableMapOf<Class<*>, Serializer<out Any>>()


   fun <T : Any> canSerialize(v: T) = serializers.containsKey(v::class.java).pq("Contains :) ${v::class.java}")


   open fun <T : Any> serialize(value: T): BsonValue {
      val serializer =
        ( serializers[value::class.java] ?: throw NotImplementedError("No Serializer has been found :( for ${value::class.java}")) as Serializer<T>
      return serializer.serialize(value )
   }

   open fun <T : Any> deserialize(type: T, serialized: BsonValue): T? {
      val serializer =
         serializers[type::class.java] ?: throw NotImplementedError("No Serializer has been found :( for $")
      return serializer.deserialize(serialized) as T


   }
}