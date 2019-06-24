object Versions {
    const val kotlin = "1.3.40"
    const val jUnit = "4.12"
    const val compileSdk = 28
    const val targetSdk = 28
    const val minSdk = 21
    const val buildTools = "29.0.0"
    const val navigation = "2.1.0-alpha05"
    const val gradle = "3.4.1"
    const val assertJ = "3.11.1"
    const val constraintLayout = "1.1.3"
    const val mockitoKotlin = "2.1.0"
}


object Dependencies {
    const val material = "com.google.android.material:material:1.1.0-alpha06"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val androidXCore = "androidx.core:core-ktx:1.0.2"
    const val appCompat = "androidx.appcompat:appcompat:1.0.2"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val legacyAndroidXSupport = "androidx.legacy:legacy-support-v4:1.0.0"
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val testRunner = "androidx.test:runner:1.2.0"
    const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
}
