plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
    id("kotlin-kapt")
}
dependencies {
    appCompat()
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.GLIDE)
}