val logback_version: String by project

plugins {

   kotlin("jvm") version "1.9.22"
   id("io.ktor.plugin") version "2.3.7"
   id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "lol.ezra"
version = "unspecified"

repositories {
   mavenCentral()
   maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
   implementation("ch.qos.logback:logback-classic:$logback_version")
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
   implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
   implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")

}

tasks.test {
   useJUnitPlatform()
}
kotlin {
   jvmToolchain(19)
}