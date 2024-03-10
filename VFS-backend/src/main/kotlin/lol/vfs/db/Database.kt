package lol.vfs.db

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import lol.vfs.db.codec.Codec
import lol.vfs.db.codec.SampleCodec
import lol.vfs.minilib.pq
import lol.vfs.model.DbRef
import lol.vfs.model.annotations.Serializable
import org.bson.BsonDocument

class Database(
   private val db: MongoDatabase = MongoClient.create("mongodb://localhost:27017").getDatabase("Default"),
   databaseScope: Database.() -> Unit
) {
   private val mapping = mutableMapOf<String, CachedCollection<*, *>>()
   private val defaultTypeMapping = mutableMapOf<Class<*>, String>()
   private val codecs = mutableMapOf<String, Codec>().apply {
      this["Sample"] = SampleCodec
   }

   init {
      databaseScope()
   }


   fun <T : Any> serialize(item: T): BsonDocument {
      val doc = BsonDocument()
      for (field in item::class.java.declaredFields.filterNot { it.name == "Companion" }) {
         field.isAccessible = true
         val f = field.get(item) ?: continue
         if (field.isAnnotationPresent(DbRef::class.java)) {
            //Logic for taking data from other database
            "ANNOTATED!!!!".pq()
            return doc.toBsonDocument()
         }
         //Check if codec with correct serializer exists
         val v = codecs.values.firstOrNull { it.canSerialize(f) }?.serialize(f)
         if (v != null) {
            doc[field.name] = v
            continue
         }
         doc[field.name] = serialize(f)
      }
      return doc
   }

//   fun <T : Any> deserialize(type: Class<T>, v: BsonDocument): T {
//      val c =
//         type.declaredConstructors.firstOrNull { it.isAnnotationPresent(Serializable::class.java) }
//            ?: type.declaredConstructors.first()
////      val items = mutableSetOf()
//      for (f in type::class.java.declaredFields)
////         c.newInstance(f)
//
//   }


   fun <Item : DBItem<ID>, ID> Database.collection(
      name: String, type: Class<Item>, collection: (CachedCollection<Item, ID>).() -> Unit = {}
   ): CachedCollection<Item, ID> {
      val c = CachedCollection<Item, ID>(this, db.getCollection(name, BsonDocument::class.java))
      mapping[name] = c
      collection(c)
      defaultTypeMapping[type] = name
      return (c)
   }


   constructor(name: String, databaseScope: Database.() -> Unit) : this(
      MongoClient.create("mongodb://localhost:27017").getDatabase(name), databaseScope
   )

   operator fun get(name: String) = mapping[name]
   operator fun <T : DBItem<ID>, ID : Any> get(forType: Class<T>): CachedCollection<DBItem<ID>, ID>? {
      val a = mapping[defaultTypeMapping[forType]]
      return a as CachedCollection<DBItem<ID>, ID>?
   }
}