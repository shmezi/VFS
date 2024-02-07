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
   implementation(project(":VFS-common"))
   implementation("io.ktor:ktor-server-core-jvm")
   implementation("io.ktor:ktor-server-auth-jvm")
   implementation("io.ktor:ktor-server-sessions-jvm")
   implementation("io.ktor:ktor-server-html-builder")
   implementation("io.ktor:ktor-server-html-builder-jvm")
   implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
   implementation("io.ktor:ktor-server-content-negotiation-jvm")
   implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
   implementation("io.ktor:ktor-server-cio-jvm")

   implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")

// https://mvnrepository.com/artifact/org.springframework/spring-jcl
   implementation("org.mindrot:jbcrypt:0.4")
}

tasks.test {
   useJUnitPlatform()
}
kotlin {
   jvmToolchain(19)
}