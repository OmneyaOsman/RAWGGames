plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("rawg-games_android_base-plugin")
}
dependencies {
    appCompat()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.GSON)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.PAGING_RUNTIME)
    room()

}