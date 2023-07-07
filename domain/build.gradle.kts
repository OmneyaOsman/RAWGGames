plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
}
android{
    namespace = "com.omni.domain"
}
dependencies {
    implementation(Dependencies.PAGING_RUNTIME)
}