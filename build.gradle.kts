plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.kshot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("org.kshot.MainKt")
}