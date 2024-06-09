plugins {
   kotlin("jvm") version "1.9.22"
   id("io.ktor.plugin") version "2.3.7"
   id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
   id("com.github.johnrengelman.shadow") version "8.1.1"
   application
}

group = "lol.ezra"
version = "unspecified"

repositories {
   mavenCentral()
   maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
   implementation(project(":VFS-common"))
   implementation("io.ktor:ktor-server-core-jvm")
   implementation("io.ktor:ktor-server-auth-jvm")
   implementation("io.ktor:ktor-server-sessions-jvm")
   implementation("io.ktor:ktor-server-html-builder")
   implementation("io.ktor:ktor-server-html-builder-jvm")
   implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
   implementation("io.ktor:ktor-server-content-negotiation")
   implementation("io.ktor:ktor-serialization-kotlinx-json")
   implementation("io.ktor:ktor-server-cio-jvm")
   implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
   implementation(kotlin("reflect"))
   implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")
   implementation("org.mindrot:jbcrypt:0.4")
}


tasks {
   application {
      mainClass = "lol.vfs.BackendKt"

   }
}
