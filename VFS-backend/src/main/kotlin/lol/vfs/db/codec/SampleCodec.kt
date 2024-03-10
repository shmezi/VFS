package lol.vfs.db.codec

import lol.vfs.db.codec.serializers.internal.StringSerializer

object SampleCodec : Codec("sample") {
   init {
      serializers[String::class.java] = StringSerializer()
   }
}