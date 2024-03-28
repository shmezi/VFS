package lol.vfs.lib.db

import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.flow.firstOrNull
import lol.vfs.lib.printing.pq
import lol.vfs.lib.serialization.getIDField

class CachedCollection<T : Any, TID : Any>(private val type: Class<T>, val name: String, private val db: Database) {
   private val collection = db.mongo.getCollection(name, type)
   private val cached = mutableMapOf<TID, T>()
   private val toUpdate = mutableSetOf<TID>()
   private var getID: ((item: T) -> TID) = { throw NotImplementedError("No @ID Annotation present!") }
   private var defaultValue: ((TID) -> T) = { throw NotImplementedError("Default value not set") }

   init {
      getID = { getIDField(type).get(it) as TID }
   }

   fun idOf(t: T) = getID(t)


   fun CachedCollection<T, TID>.default(scope: (TID) -> T) {
      defaultValue = scope
   }


   suspend fun get(id: TID): T? {
      if (cached.containsKey(id)) return cached[id]
      "[$name] - $id".pq("Cached")
//      val item = db.deserialize<T, TID>(
//         type,
//         collection.find(eq("_id", id)).firstOrNull()?.asDocument()
//            ?: return null
//      )
      return collection.find(eq("_id", id)).firstOrNull()
   }

   suspend fun update(item: T) {
      toUpdate.add(idOf(item))
      //Add item to the cache plus add to the to update list
   }

   suspend fun update(id: TID) {
      toUpdate.add(id)
      //Add item to the cache plus add to the to update list
   }


   suspend fun getOrDefault(id: TID, scope: T.() -> Unit): T {
      val item = get(id)
      if (item != null) return item
      val newItem = defaultValue(id)
      cached[id] = newItem
      scope(newItem)
//      collection.insertOne(db.serialize<T, TID>(newItem) as BsonDocument)
      collection.insertOne(newItem)
      return newItem
   }

}