plugins {
    java
}

group = "dev.danipraivet"
version = "1.0.0"

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dev.danipraivet.juegodelucha.Main"
        )
    }
}