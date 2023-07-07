plugins {
    id("com.android.application")
    id("rawg-games_android_base-plugin")
    id("androidx.navigation.safeargs")
}


dependencies {
    appCompat()
    androidXNavigation()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.ANDROID_MATERIAL_DESIGN)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":feature-games-list"))
    implementation(project(":feature-favorite-genere"))
    implementation(project(":feature-splash"))
    implementation(project(":featur-search"))

}
android {
    buildFeatures {
        viewBinding = true
    }
}