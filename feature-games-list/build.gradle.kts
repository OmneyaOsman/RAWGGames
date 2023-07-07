plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("rawg-games_android_base-plugin")
}
android{
    namespace="com.omni.feature_games_list"
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    appCompat()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.RECYCLERVIEW)
    implementation(Dependencies.ANDROID_MATERIAL_DESIGN)
    implementation(Dependencies.PAGING_RUNTIME)
}