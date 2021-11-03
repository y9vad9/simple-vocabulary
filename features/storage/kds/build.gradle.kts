plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.Library)
}

dependencies {
    implementation(project(Deps.Modules.Features.Storage.Core))
    implementation(Deps.Libs.KotlinGang.KotlinDataStorage.SharedPreferences)
}

android {
    compileSdk = AppInfo.Android.TargetSdk
}