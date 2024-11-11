plugins {
    kotlin("jvm") version "2.0.20"

    kotlin("plugin.serialization") version "2.0.21"
}

group = "effie.soft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.13")

    implementation(platform("org.dizitart:nitrite-bom:4.3.0"))
    implementation("org.dizitart:potassium-nitrite")
    implementation("org.dizitart:nitrite-mvstore-adapter")

    implementation("io.klogging:klogging-jvm:0.7.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}