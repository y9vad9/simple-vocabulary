plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.App)
}

group = AppInfo.PACKAGE
version = AppInfo.VERSION

dependencies {
    implementation(Deps.Libs.KotlinGang.KotlinDataStorage.SharedPreferences)
    implementation(Deps.Libs.Androidx.Compose.UI)
    implementation(Deps.Libs.Androidx.AppCompat)
    implementation(Deps.Libs.Androidx.Material)
    implementation(Deps.Libs.Androidx.Compose.Accompanist.SystemUiController)
    implementation(Deps.Libs.Androidx.Compose.Foundation)
    implementation(Deps.Libs.Androidx.Compose.Icons)
    implementation(Deps.Libs.Androidx.Compose.Material)
    implementation(Deps.Libs.Androidx.Compose.ExtendedIcons)
    implementation(Deps.Libs.Androidx.Compose.Activity)
    implementation(Deps.Libs.Kotlinx.Coroutines)
    implementation(project(Deps.Modules.Core))
    implementation(Deps.Libs.Androidx.LifecycleKtx)
    implementation(Deps.Libs.Androidx.LifecycleCompose)
    implementation(Deps.Libs.Androidx.Navigation.Compose)
}

android {
    compileSdk = AppInfo.Android.TargetSdk

    defaultConfig {
        targetSdk = AppInfo.Android.TargetSdk
        minSdk = AppInfo.Android.MinSdk
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.31"
        kotlinCompilerExtensionVersion = "1.1.0-alpha06"
    }

    kotlinOptions {
        languageVersion = "1.5"
        jvmTarget = "1.8"
    }
}