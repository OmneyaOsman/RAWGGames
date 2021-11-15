plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
}
dependencies {
    implementation(project(":core"))
    implementation(Dependencies.PAGING_RUNTIME)
}