package lol.vfs.lib.serialization.testing

import kotlinx.coroutines.runBlocking
import lol.vfs.lib.db.Database
import org.bson.BsonString

suspend fun main() {
   Database {
      serializer<String> {
         serialize {
            BsonString(it as String)
         }
         deserialize {
            it.asString().value
         }
      }

      runBlocking {
         collection(Parent::class.java, "test") {
            default {
               Parent(
                  it, "test", Kid("129381237124", "shmezKid", "shmezKidLastname"),
                  Kid("129381237124", "shmezKid", "shmezKidLastname")
               )
            }

         }.getOrDefault("123123412") {

         }


      }
   }


}