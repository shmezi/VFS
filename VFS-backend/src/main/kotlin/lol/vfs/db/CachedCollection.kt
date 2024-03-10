package lol.vfs.db

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.bson.BsonDocument
import org.bson.Document

class CachedCollection<T : Any, ID>(private val db: Database, private val collection: MongoCollection<BsonDocument>) {
   private val cached = mutableMapOf<ID, T>()
   private val updateList = mutableSetOf<ID>()
   private var defaultValue: ((ID) -> T)? = null

   private fun getDefault(id: ID) = defaultValue?.invoke(id)
      ?: throw NotImplementedError("Default value has not been implement for CachedCollection\"${collection.namespace.collectionName}\"")

   fun CachedCollection<T, ID>.default(id: ID.() -> T) {
      defaultValue = id
   }


   /**
    * Retrieves an item from the database
    */
   suspend fun retrieve(id: ID): T? {
      val c = cached[id]
      if (c != null) return c

      val s = collection.find(eq("_id", id)).firstOrNull()
      s
      return null
   }

   suspend fun insert(item: T) {
      collection.insertOne(db.serialize(item))
   }


   suspend fun get(id: ID, item: T.() -> Unit) {
      val i = retrieve(id)
      if (i != null) item(i)
      item(getDefault(id))
      updateList.add(id)
   }


   suspend fun set(id: ID, value: T) {
      TODO("Set data that could be cached")
   }

   suspend fun runUpdate() {
      runBlocking {
         for (item in cached.filter { updateList.contains(it.key) }) {
//            collection.findOneAndReplace(eq("_id", item.key), item.value)
            TODO("FIX LATER :) -> that's your job now idiot! :)))))))) ")
         }
      }
      TODO("Will run a database update")
   }

   fun clearCacheSafely() {
      cached.filter { !updateList.contains(it.key) }.keys.forEach {
         cached.remove(it)
      }
   }

   fun forceClearCache() {
      cached.keys.forEach {
         cached.remove(it)
      }

   }


}