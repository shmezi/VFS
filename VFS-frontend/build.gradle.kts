import org.jetbrains.compose.desktop.application.dsl.TargetFormat


plugins {
   kotlin("jvm") version "1.9.22"
   id("org.jetbrains.compose") version "1.5.12"
   id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"

}

group = "lol.ezra"
version = "1.0-SNAPSHOT"

repositories {
   mavenCentral()
   maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
   google()
}

dependencies {
   // Note, if you develop a library, you should use compose.desktop.common.
   // compose.desktop.currentOs should be used in launcher-sourceSet
   // (in a separate module for demo project and in testMain).
   // With compose.desktop.common you will also lose @Preview functionality
   implementation(compose.desktop.currentOs)
   implementation("io.ktor:ktor-client-apache:2.3.7")

   val ktor_version = "2.3.7"
   val logback_version = "1.4.14"
   // Multiplatform

   val voyagerVersion = "1.0.0"
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
   // Navigator
   implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
   implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
   implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
   implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
   implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

   implementation("io.ktor:ktor-client-core:$ktor_version")
   implementation("io.ktor:ktor-client-cio:$ktor_version")
   implementation("io.ktor:ktor-client-cio-jvm:2.3.7")
   implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

   implementation("ch.qos.logback:logback-classic:$logback_version")
   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
   implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
   implementation(project(":VFS-common"))
   implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
   implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
}

compose.desktop {
   application {
      mainClass = "MainKt"

      nativeDistributions {
         targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
         packageName = "VFS-frontend"
         packageVersion = "1.0.0"
      }
   }
}
