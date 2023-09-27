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
    //implementation(group: 'org.slf4j', name: 'slef4j-nop', version: '2.0.9')
    //implementation 'org.json:json:20230618'

}

tasks.test {
    useJUnitPlatform()
}