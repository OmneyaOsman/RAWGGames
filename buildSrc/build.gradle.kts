plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.0.2")
    implementation(kotlin("gradle-plugin", "1.5.31"))
}
gradlePlugin {
    plugins {
        register("rawg-games_android_base-plugin") {
            id = "rawg-games_android_base-plugin"
            implementationClass = "gradle.RAWGGamesAndroidPlugin"
        }
    }
}