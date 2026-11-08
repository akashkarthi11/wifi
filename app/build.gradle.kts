plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.karthi.panasonicremote"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.karthi.panasonicremote"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
}
