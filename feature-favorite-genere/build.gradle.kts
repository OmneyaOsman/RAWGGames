plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
    id("kotlin-kapt")
}
android{
    namespace="com.omni.feature_favorite_genere"
}
dependencies {
    appCompat()
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":navigation"))

    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.GLIDE)
}