import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(BuildDependencies.androidGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath(BuildDependencies.navigationGradle)
        classpath(BuildDependencies.dependencyGraphGenerator)
        classpath(BuildDependencies.googleGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

}

plugins {
    id("com.vanniktech.dependency.graph.generator") version BuildVersionsDependencies.DEPENDENCY_GRAPH_GENERATOR
    id("com.github.ben-manes.versions") version BuildVersionsDependencies.UPDATE_DEPENDENCIES_PLUGIN_VERSION
    id("org.jlleitschuh.gradle.ktlint") version BuildVersionsDependencies.KTLINT
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.named("dependencyUpdates", DependencyUpdatesTask::class).configure {
    // Example 1: reject all non stable versions
    rejectVersionIf {
        isNonStable(candidate.version)
    }

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "dependencies-update-report"
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
