package lol.vfs.lib.serialization

import lol.vfs.lib.db.Id
import java.lang.reflect.Field

fun <T : Any> getIDField(type: Class<T>): Field {
   for (field in type.declaredFields) {
      field.isAccessible = true
      if (!field.isAnnotationPresent(Id::class.java)) continue
      return field
   }
   throw NotImplementedError("No @ID Field set for class ${type.name}")
}