package lol.vfs.lib.serialization

import lol.vfs.lib.db.Database
import lol.vfs.lib.db.Id
import lol.vfs.lib.serialization.base.IntSerializer
import lol.vfs.lib.serialization.base.StringSerializer
import lol.vfs.lib.serialization.serializer.Serializer
import org.bson.BsonDocument
import org.bson.BsonValue

class Codec(val database: Database? = null) {

   private val weightMap = mutableMapOf<Class<*>, MutableList<Pair<Int, Serializer<out Any>>>>()
   private val serializerMap = mutableMapOf<Class<*>, Serializer<out Any>>()
//   private val referenceSerializerMap = mutableMapOf<String, ReferenceSerializer<out Any, out Any>>()

   fun <T : Any> addSerializer(clazz: Class<T>, serializer: Serializer<T>, weight: Int) {
      val weights = weightMap.getOrPut(clazz) { mutableListOf() }
      weights.add(Pair(weight, serializer))
      var big = 0
      weights.forEach {
         if (it.first > big) big = it.first
      }
      serializerMap[clazz] = weights.first { it.first == big }.second
   }

   init {
      addSerializer(Int::class.java, IntSerializer(), 0)
      addSerializer(String::class.java, StringSerializer(), 0)
   }

   fun <T : Any, TID : Any> getSerializer(clazz: Class<T>): Serializer<T> {
      val s = serializerMap[clazz]
      if (s != null) return s as Serializer<T>
      return generateSerializer<T, TID>(clazz)
   }

//   fun <T : Any, TID : Any> generateReferenceSerializer(name: String) {
//      val s = ReferenceSerializer(
//         (database?.getCollection(name)
//            ?: throw NullPointerException("Could not find given ")) as CachedCollection<T, TID>
//      )
//   }


   private fun <T : Any, TID : Any> generateSerializer(
      clazz: Class<T>
   ): Serializer<T> {
      //Mappings of parameter to serializer / deserializer
      val serializers = mutableMapOf<String, (input: Any) -> BsonValue>()
      val deserializers = mutableListOf<Pair<String, (value: BsonValue) -> Any>>()
      for (field in clazz.declaredFields) {
         field ?: throw NullPointerException("Could not find a field in ${clazz.name}")
         field.isAccessible = true
         var name = field.name
         when {
            field.isAnnotationPresent(DbRef::class.java) -> {
            }

            field.isAnnotationPresent(Id::class.java) -> {
               name = "_id"
            }

            field.isEnumConstant -> {
               serializers[name] = { StringSerializer().serialize(field.get(it)) }

               continue
            }
         }
         val serializer = getSerializer<T, TID>(field.type as Class<T>)
         serializers[name] = {
            serializer.serialize(field.get(it))
         }
         deserializers.add(Pair(name) {
            getSerializer<T, TID>(field.type as Class<T>).deserialize(it)
         })
      }


      val s = object : Serializer<T> {

         override fun serialize(item: Any): BsonValue {
            val v = BsonDocument()
            for (serializer in serializers) v[serializer.key] = serializer.value(item)
            return v
         }

         override fun deserialize(value: BsonValue): T {

            val params = deserializers.map {
               if (value.isDocument) it.second(value.asDocument()[it.first]!!)
               else value

            }
            return clazz.constructors.first().newInstance(*params.toTypedArray()) as T
         }
      }
      serializerMap[clazz] = s
      return s
   }


}