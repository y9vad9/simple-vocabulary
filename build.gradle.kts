plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.App)
}

group = AppInfo.PACKAGE
version = AppInfo.VERSION

android {
    compileSdk = AppInfo.Android.TargetSdk
}
