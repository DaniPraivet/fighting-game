import kotlin.io.path.createDirectory
import kotlin.io.path.exists

plugins {
    java
    application
}

val projectVersion: String by extra

group = "dev.danipraivet"
version = projectVersion
sourceSets.main.get().resources.srcDirs("src/main/resources")

repositories {
    mavenCentral()
}

dependencies {
    // Project dependencies
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dev.danipraivet.juegodelucha.Main"
        )
    }
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

application {
    mainClass.set("dev.danipraivet.juegodelucha.Main")
}

tasks.named<JavaExec>("run") {
    val path = rootDir.toPath().resolve("run")
    
    workingDir = path.toFile()
    standardInput = System.`in`
    standardOutput = System.out
    
    if (!path.exists()) path.createDirectory()
}