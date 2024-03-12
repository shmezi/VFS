package lol.vfs.serialization

import lol.vfs.minilib.pq
import lol.vfs.serialization.base.IntSerializer
import lol.vfs.serialization.base.StringSerializer
import org.bson.BsonDocument
import org.bson.BsonValue

class Codec {

   private val weightMap = mutableMapOf<Class<*>, MutableList<Pair<Int, Serializer<out Any>>>>()
   private val serializerMap = mutableMapOf<Class<*>, Serializer<out Any>>()


   fun <T : Any> addSerializer(clazz: Class<T>, serializer: Serializer<T>, weight: Int) {
      val weights = weightMap.getOrPut(clazz) { mutableListOf() }
      weights.add(Pair(weight, serializer))
      var big = 0
      weights.forEach {
         if (it.first > big)
            big = it.first
      }
      serializerMap[clazz] = weights.first { it.first == big }.second
   }

   init {
      addSerializer(Int::class.java, IntSerializer(), 0)
      addSerializer(String::class.java, StringSerializer(), 0)
   }

   fun <T : Any> getSerializer(clazz: Class<T>): Serializer<T> {
      val s = serializerMap[clazz]
      if (s != null) return s as Serializer<T>
      return generateSerializer(clazz)
   }

   private fun <T : Any> generateSerializer(clazz: Class<T>, ref: String? = null): Serializer<T> {
      "Generating serializer for ${clazz.name}".pq("LOG")
      if (ref != null) {
         val serializer = getSerializer(clazz)
         val s = object : Serializer<T> {
            override val reference = true

            override fun serialize(item: Any): BsonValue {
               //For references object id is stored
               return serializer.serialize(item)
            }

            override fun deserialize(value: BsonValue): T {
               //For references object is taken from other collection and
               return serializer.deserialize(value)
            }

         }
         serializerMap[clazz] = s
      }

      val serializers = mutableMapOf<String, (input: Any) -> BsonValue>()
      val deserializers = mutableListOf<Pair<String, (value: BsonValue) -> Any>>()
      for (field in clazz.declaredFields) {
         field ?: throw NullPointerException("Could not find a field in ${clazz.name}")
         field.isAccessible = true
         if (field.isAnnotationPresent(DbRef::class.java) && ref == null) {
            return generateSerializer(clazz, field.getAnnotation(DbRef::class.java).collection)
         }
         serializers[field.name] = {
            getSerializer(field.type).serialize(field.get(it))
         }
         deserializers.add(Pair(field.name) {
            getSerializer(field.type).deserialize(it)
         })


      }

      val s = object : Serializer<T> {
         override val reference = false
         override fun serialize(item: Any): BsonValue {
            val v = BsonDocument()
            for (serializer in serializers) v[serializer.key] = serializer.value(item)
            return v
         }

         override fun deserialize(value: BsonValue): T {

            val params = deserializers.map {
               if (value.isDocument)
                  it.second(value.asDocument()[it.first]!!)
               else value

            }
            return clazz.constructors.first().newInstance(*params.toTypedArray()) as T
         }
      }
      serializerMap[clazz] = s
      return s
   }


}