pluginManagement {
    plugins {
        kotlin("jvm") version "2.3.10"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "pertemuan-2-coroutines-flow"

include(
    "handson1-latihan",
    "handson2-latihan",
    "handson3-latihan"
)
