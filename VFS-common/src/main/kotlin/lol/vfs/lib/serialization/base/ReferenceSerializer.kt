//package lol.vfs.serialization.base
//
//import lol.vfs.db.CachedCollection
//import lol.vfs.serialization.serializer.Serializer
//import org.bson.BsonString
//import org.bson.BsonValue
//
//class ReferenceSerializer<T : Any, TID : Any>(private val collection: CachedCollection<T, TID>)  : Serializer<T>{
//
//   suspend fun serialize(item: T): TID {
//
//   }
//
//   suspend fun deserialize(id: TID): T {
//      return collection.getOrDefault(id) {}
//   }
//
//   override fun serialize(item: Any): BsonValue {
//          //Serialize item and return TID while also updating item in cached collection
//      collection.update(item as T)
//      return BsonString(item)
//   }
//
//   override fun deserialize(value: BsonValue): T {
//      TODO("Not yet implemented")
//   }
//
//
//}