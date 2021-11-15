//plugins {
//    id 'com.android.application'
//    id 'kotlin-android'
//}
//
//android {
//    compileSdk 30
//
//    defaultConfig {
//        applicationId "com.omni.rawggames"
//        minSdk 23
//        targetSdk 30
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_11
//        targetCompatibility JavaVersion.VERSION_11
//    }
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_11.toString()
//    }
//}
//
//dependencies {
//
//    implementation 'androidx.core:core-ktx:1.6.+'
//    implementation 'androidx.appcompat:appcompat:1.3.1'
//    implementation 'com.google.android.material:material:1.4.0'
//    implementation 'androidx.constraintlayout:constraintlayout:2.1.1'
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//}

plugins {
    id("com.android.application")
    id("rawg-games_android_base-plugin")
    id("androidx.navigation.safeargs")
}


dependencies {
    appCompat()
    androidXNavigation()
    implementation(Dependencies.CONSTRAINTLAYOUT)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.ANDROID_MATERIAL_DESIGN)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":feature-games-list"))
    implementation(project(":feature-favorite-genere"))
    implementation(project(":feature-splash"))

}
android {
    buildFeatures {
        viewBinding = true
    }
}