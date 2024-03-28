package lol.vfs.pages.user

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import lol.vfs.lib.printing.pq
import lol.vfs.model.organizational.Class
import lol.vfs.url

object User : Screen {
   @Composable
   override fun Content() {
      runBlocking {
         val c = HttpClient(CIO) {
            install(ContentNegotiation) {
               json()
            }
            install(HttpCookies)

         }
         val s: Class = c.post("cl".url()) {
            contentType(ContentType.Application.Json)
         }.body()
         s.pq()
      }

   }
}