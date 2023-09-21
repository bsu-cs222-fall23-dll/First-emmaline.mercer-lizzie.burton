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
    implementation group: 'com.jayway.jsonpath', name: 'json-path', version: '2.8.0'

}

tasks.test {
    useJUnitPlatform()
}