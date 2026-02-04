plugins {
    application
}

group = "com.example"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenLocal()  // Check local maven first for published library versions
    mavenCentral()
}

// Library version - change this to test different versions
val libraryVersion: String = project.findProperty("libraryVersion")?.toString() ?: "1.0.0"

dependencies {
    // External dependency on the library (published to mavenLocal)
    implementation("com.example:library:$libraryVersion")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

application {
    mainClass.set("com.example.consumer.Application")
}
