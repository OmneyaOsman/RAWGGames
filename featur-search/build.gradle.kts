plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
}
dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    appCompat()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.ANDROID_MATERIAL_DESIGN)
    lifecycle()
}