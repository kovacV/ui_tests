plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.uitests"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("com.codeborne:selenide:7.0.4")
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}