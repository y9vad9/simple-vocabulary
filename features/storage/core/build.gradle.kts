plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.Library)
}

dependencies {
    api(project(Deps.Modules.Core))
    api(Deps.Libs.Kotlinx.Coroutines)
}

android {
    compileSdk = AppInfo.Android.TargetSdk
}