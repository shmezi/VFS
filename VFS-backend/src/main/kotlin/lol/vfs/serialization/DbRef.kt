package lol.vfs.serialization

@Target(AnnotationTarget.FIELD)
annotation class DbRef(val collection: String)
