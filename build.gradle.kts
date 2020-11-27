import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.20"
}

group = "me.insolor"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("javafx_kotlin.App")
}

repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(kotlin("test-junit"))
//}
//
//tasks.test {
//    useJUnit()
//}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
