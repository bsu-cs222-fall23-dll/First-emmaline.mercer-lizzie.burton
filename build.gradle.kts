plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.jayway.jsonpath:json-path:2.8.0")
    implementation("net.minidev:json-smart:2.5.0")

}

tasks.test {
    useJUnitPlatform()
}