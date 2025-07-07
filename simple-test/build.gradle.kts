plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":library"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(17)
} 