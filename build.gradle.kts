import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.20"
}

group = "me.insolor"
version = "1.0-SNAPSHOT"

application {
    // mainClass.set("javafx_kotlin.App") // Kotlin app main class
    mainClass.set("javafx_kotlin.JavaApp") // Java app main class
}

repositories {
    mavenCentral()
}

dependencies {
    // testImplementation(kotlin("test-junit"))
    implementation("no.tornado", "tornadofx", "1.7.20")
}

// tasks.test {
//     useJUnit()
// }

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
