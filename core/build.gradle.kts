plugins {
    id("com.android.library")
    id("rawg-games_android_base-plugin")
}
android{
    defaultConfig{

        buildConfigField(
            "String",
            "BASE_URL",
            project.properties["BASE_URL"] as String
        )

        buildConfigField(
            "String",
            "API_KEY",
            project.properties["API_KEY"] as String
        )
    }
}
dependencies {
    appCompat()
    retrofit()
}