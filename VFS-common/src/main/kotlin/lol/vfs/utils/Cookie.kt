package lol.vfs.utils

private const val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz"
fun randomString(length: Int) = (0 .. length).map { chars.random() }.joinToString("")

fun bakeCookie() = randomString(16)