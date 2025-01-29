package lol.vfs.lib.db

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import lol.vfs.lib.serialization.serializer.Serializer
import lol.vfs.lib.serialization.serializer.SerializerBuilder
import org.bson.BsonValue

class Database(
   name: String = "Default",
   host: String = "mongodb://localhost:27017",
   val mongo: MongoDatabase = MongoClient.create(host).getDatabase(name),
   scope: Database.() -> Unit
) {
   private val collections = mutableMapOf<String, CachedCollection<*, *>>()
//   private val codec = Codec(this)

   fun getCollection(name: String) = collections[name]

   init {
      scope()
   }

   fun <TID : Any, T : Any> Database.collection(
      type: Class<T>,
      name: String,
      scope: CachedCollection<T, TID>.() -> Unit
   ): CachedCollection<T, TID> {
      val collection = CachedCollection<T, TID>(type, name, this)
      scope(collection)
      collections[name] = collection
      return collection
   }

//
//   fun <T : Any, TID : Any> serialize(item: T) =
//      codec.getSerializer<T, TID>(item::class.java as Class<T>).serialize(item)
//
//   fun <T : Any, TID : Any> deserialize(clazz: Class<T>, value: BsonValue) =
//      codec.getSerializer<T, TID>(clazz).deserialize(value)

   fun <T : Any> Database.serializer(serializerBuilderScope: SerializerBuilder<T>.() -> Unit) {
      val builder = SerializerBuilder<T>()
      serializerBuilderScope(builder)
      object : Serializer<T> {


         override fun serialize(item: Any) =
            builder.serializer?.invoke(item) ?: throw NotImplementedError("Serializer not set!")

         override fun deserialize(value: BsonValue) =
            builder.deserializer?.invoke(value) ?: throw NotImplementedError("Deserializer not set!")


      }
   }


}