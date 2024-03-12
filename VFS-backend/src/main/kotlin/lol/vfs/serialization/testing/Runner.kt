package lol.vfs.serialization.testing

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.first
import lol.vfs.minilib.pq
import lol.vfs.serialization.Codec
import lol.vfs.utils.randomString
import org.bson.BsonDocument

suspend fun main() {
   val parentA = Parent(
      randomString(6),
      "shmezi",
      Kid("129381237124", "shmezKid", "shmezKidLastname"),
      Kid("129381237124", "shmezKid", "shmezKidLastname")
   )
   val parentB = Parent(
      randomString(6),
      "shmezi",
      Kid("129381237124", "shmezKid", "shmezKidLastname"),
      Kid("129381237124", "shmezKid", "shmezKidLastname")
   )

   val parentC = Parent(
      randomString(6),
      "shmezi",
      Kid("129381237124", "shmezKid", "shmezKidLastname"),
      Kid("129381237124", "shmezKid", "shmezKidLastname")
   )

   val c = Codec()
   val db = MongoClient.create("mongodb://localhost:27017").getDatabase("Default")
   val collection = db.getCollection<BsonDocument>("parenttest")
   val serializer = c.getSerializer(parentA::class.java)
   collection.insertOne(serializer.serialize(parentA).asDocument())
   collection.insertOne(serializer.serialize(parentB).asDocument())
   collection.insertOne(serializer.serialize(parentC).asDocument())

   val s = serializer.deserialize(collection.find(eq("_id", "4X3MpST")).first())
   s.pq()
}