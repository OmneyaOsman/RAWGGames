package gradle


import com.android.build.api.dsl.Lint
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.builder.model.ApiVersion
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*


class RAWGGamesAndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("kotlin-android")
//        project.plugins.apply("kotlin-kapt")

        project.configureAndroid()
        project.configureDependencies()

        project.afterEvaluate {
            with(project) {
                tasks {
                    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                        with(kotlinOptions) {
                            jvmTarget = JavaVersion.VERSION_17.toString()
                            freeCompilerArgs = listOf(
                                "-Xallow-result-return-type",
                                "-Xopt-in=kotlin.time.ExperimentalTime"
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun Project.configureAndroid() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(AndroidSettings.COMPILE_SDK)
    namespace = "com.omni.rawggames"

    defaultConfig {
        versionCode = AndroidSettings.APP_VERSION_CODE
        versionName = AndroidSettings.APP_VERSION_NAME
        setProperty("archivesBaseName", "${parent?.name}-v${versionName}(${versionCode})")

        minSdk = AndroidSettings.MIN_ANDROID_SDK
        targetSdk = AndroidSettings.TARGET_ANDROID_SDK

        testInstrumentationRunner = AndroidSettings.testInstrumentationRunner
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = false
        isCheckDependencies = true
        xmlReport = false
        htmlReport = true
        htmlOutput = file("${project.rootDir}/build/reports/android-lint.html")
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "key0"
            keyPassword = "omni@dmin"
            storeFile = file("../rawg-games-debug-key.jks")
            storePassword = "omni@dmin"
        }
        create("release") {
            keyAlias = "key0"
            keyPassword = "omni@dmin"
            storeFile = file("../rawg-games-debug-key.jks")
            storePassword = "omni@dmin"
        }
    }

    // Configure common proguard file settings.
    val proguardFile = "proguard-rules.pro"
    when (this) {
        is LibraryExtension -> defaultConfig {
            consumerProguardFiles(proguardFile)

        }

        is AppExtension -> {
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        proguardFile
                    )
                    signingConfig = signingConfigs.getByName("release")
                }
                getByName("debug") {
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        proguardFile
                    )
                    signingConfig = signingConfigs.getByName("debug")
                }
            }
        }
    }


}

private fun Project.configureDependencies() = dependencies {
    fun kapt(definition: Any) = "kapt"(definition)
    fun implementation(definition: Any) = "implementation"(definition)
    fun testImplementation(definition: Any) = "testImplementation"(definition)
    fun androidTestImplementation(definition: Any) = "androidTestImplementation"(definition)

    implementation(kotlin("stdlib"))
    implementation(Dependencies.ANDROID_CORE_KTX)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.ANDROIDX_FRAGMENT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.KOIN_ANDROID)
    implementation(Dependencies.NAVIGATION_FRAGMENTX)
    implementation(Dependencies.NAVIGATION_UI)
}