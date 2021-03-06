plugins {
  java//delete?
  kotlin("jvm") version "1.4.30"
  kotlin("plugin.serialization") version "1.4.30"
  application // use at dist.sh
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

application {
  mainClassName = "org.sample.StarterKt"
  applicationName = "app"
}

tasks.getByName<JavaExec>("run").workingDir = rootProject.projectDir
tasks.withType<Test> {
  testLogging {
    showStandardStreams = true
  }
}

group = "org.sample"
version = "1.0-SNAPSHOT"

repositories {
  jcenter()//todo remove
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  val KTOR_VERSION = "1.5.1"
  val COROUTINES_VERSION = "1.4.2"

  implementation(if (true) "ch.qos.logback:logback-classic:1.2.3" else "org.slf4j:slf4j-simple:1.7.28")
  implementation("io.ktor:ktor-server-netty:$KTOR_VERSION")
  implementation("io.ktor:ktor-server-cio:$KTOR_VERSION")
  implementation("io.ktor:ktor-client-core:$KTOR_VERSION")
  implementation("io.ktor:ktor-client-apache:$KTOR_VERSION")
  implementation("io.ktor:ktor-html-builder:$KTOR_VERSION")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_VERSION")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}
