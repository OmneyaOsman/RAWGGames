import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Androidx.APP_COMPAT}"
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Androidx.CORE_KTX}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"

    const val RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
    const val CONSTRAINTLAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROID_MATERIAL_DESIGN =
        "com.google.android.material:material:${Versions.MATERIAL_ANDROID}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
    const val SWIPE_TO_REVEAL =
        "com.chauthai.swipereveallayout:swipe-reveal-layout:${Versions.SWIPE_TO_REVEAL}"

    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"


    const val ANDROID_LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime:${Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_EX = "androidx.lifecycle:lifecycle-extensions:${Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_LIVEDATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Androidx.LIFECYCLE}"

    const val ANDROIDX_FRAGMENT =
        "androidx.fragment:fragment-ktx:${Androidx.ANDROIDX_FRAGMENT}"

    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val CRASHLYTICS = "com.crashlytics.sdk.android:crashlytics:${Versions.CRASHLYTICS}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_SCALAR =
        "com.squareup.retrofit2:converter-scalars:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val RETROFIT_COROUTINES_ADAPTER =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT_COROUTINES_ADAPTER}"
    const val OKHTTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"

    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_ANDROID}"

    const val PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    //region Room Lib
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER: String = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_PAGING = "androidx.room:room-paging:${Versions.ROOM}"
    const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM}"
    //endregion
}

object BuildDependencies {
    const val androidGradle =
        "com.android.tools.build:gradle:${BuildVersionsDependencies.ANDROID_GRADLE}"
    const val navigationGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
    const val googleGradle =
        "com.google.gms:google-services:${BuildVersionsDependencies.GOOGLE_GRADLE}"
    const val dependencyGraphGenerator =
        "com.vanniktech:gradle-dependency-graph-generator-plugin:${BuildVersionsDependencies.DEPENDENCY_GRAPH_GENERATOR}"


}

object TestDependencies {

    object AndroidX {
        const val core =
            "androidx.test:core:${Versions.androidxTest}"
        const val coreKtx =
            "androidx.test:core-ktx:${Versions.androidxTest}"
        const val runner =
            "androidx.test:runner:${Versions.androidxTest}"
        const val rules =
            "androidx.test:rules:${Versions.androidxTest}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.androidxTest}"
        const val junit =
            "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    }

    const val TEST_KOTLINX_COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_ANDROID}"

    const val JUNIT = "junit:junit:${Versions.junit}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.mockito}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.mockito}"
    const val MOCKK = "io.mockk:mockk:${Versions.mockk}"
    const val TURBIN = "app.cash.turbine:turbine:${Versions.turbin}"
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_CORE)
}


fun DependencyHandler.appCompat() {
    implementation(Dependencies.ANDROID_APP_COMPAT)
}

fun DependencyHandler.mockito() {
    testImplementation(TestDependencies.MOCKITO_CORE)
    testImplementation(TestDependencies.MOCKITO_INLINE)// To mock final classes
    testImplementation(TestDependencies.MOCKK)
}

fun DependencyHandler.androidXNavigation() {
    implementation(Dependencies.NAVIGATION_FRAGMENTX)
    implementation(Dependencies.NAVIGATION_UI)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.ANDROID_LIFECYCLE_RUNTIME)
    implementation(Dependencies.ANDROID_LIFECYCLE_EX)
    implementation(Dependencies.ANDROID_LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.ANDROID_LIFECYCLE_LIVEDATA)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
//    implementation(Dependencies.RETROFIT_CONVERTER_SCALAR)
//    implementation(Dependencies.RETROFIT_COROUTINES_ADAPTER)
    implementation(Dependencies.OKHTTP_LOGGER)
    implementation(Dependencies.GSON)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
}


fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_PAGING)
    kapt(Dependencies.ROOM_COMPILER)
    androidTestImplementation(Dependencies.ROOM_TESTING)
}

fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(depName: Any) = add("testImplementation", depName)
fun DependencyHandler.androidTestImplementation(depName: Any) =
    add("androidTestImplementation", depName)