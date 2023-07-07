plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
    id("kotlin-kapt")
}
android{
    namespace = "com.omni.core"
}
dependencies {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
}