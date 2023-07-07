plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
    id("kotlin-kapt")
}
android{
    namespace = "com.omni.feature_splash"
}
dependencies {
    implementation(Dependencies.LOTTI)
    implementation(project(":domain"))
    implementation(project(":navigation"))
}