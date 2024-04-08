plugins {
    kotlin("jvm") version "1.9.21"
    id("io.qameta.allure") version "2.11.2"
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

        //TODELETE
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("org.apache.httpcomponents:httpcore:4.4.14")
    // https://mvnrepository.com/artifact/commons-codec/commons-codec
    implementation("commons-codec:commons-codec:1.15")
}

allure {
    report {
        version.set("2.21.0")
    }
    adapter {
        aspectjWeaver.set(true)
        aspectjVersion.set("1.9.19")
        frameworks {
            junit5 {
                adapterVersion.set("1.9.19")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}