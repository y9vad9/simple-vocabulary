pluginManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "4.2.2"
        id("org.jetbrains.kotlin.android") version "1.5.31"
        id("org.jetbrains.kotlin.multiplatform") version "1.5.31"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.kotlingang.fun")
        maven("https://jitpack.io")
        gradlePluginPortal()
    }
}

rootProject.name = "simple-vocabulary"

includeBuild("build-logic/dependencies")
includeBuild("build-logic/configuration")

include(":core")
include(":features:storage:core")
include(":features:storage:kds")
include(":features:storage:room")
