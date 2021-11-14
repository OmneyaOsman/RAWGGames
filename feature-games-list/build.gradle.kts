plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("rawg-games_android_base-plugin")
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    appCompat()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.PAGING_RUNTIME)
}