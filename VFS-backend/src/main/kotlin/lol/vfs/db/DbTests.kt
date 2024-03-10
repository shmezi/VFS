package lol.vfs.db


suspend fun main() {
   lateinit var players: CachedCollection<Player, String>


   val s = Database("VFS") {
      players = collection("test", Player::class.java) {
         default {
            Player(this, Son("shmezi", "kid"), "shmezi")
         }
      }
      collection("asd", Player::class.java)
   }
   players.insert(Player("Hello:)", Son("asd", "kid"), "shmezi"))


}