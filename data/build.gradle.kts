plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("rawg-games_android_base-plugin")
}
android {
    defaultConfig {

        buildConfigField(
            "String",
            "BASE_URL",
            project.properties["BASE_URL"] as String
        )

        buildConfigField(
            "String",
            "API_KEY",
            project.properties["API_KEY"] as String
        )
    }
}
dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    retrofit()
    implementation(Dependencies.PAGING_RUNTIME)
    room()
    implementation(Dependencies.DATA_STORE)

    implementation(TestDependencies.runner)
    implementation(TestDependencies.junit)
}