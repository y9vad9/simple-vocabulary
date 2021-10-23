plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.Library)
    id(Deps.Plugins.Serialization.Id)
}

dependencies {
    implementation(Deps.Libs.Androidx.Compose.UI)
    implementation(Deps.Libs.Androidx.LifecycleKtx)
    implementation(Deps.Libs.Androidx.LifecycleViewModel)
    implementation(Deps.Libs.Kotlinx.Coroutines)
    implementation(Deps.Libs.Kotlinx.Serialization)
}

android {
    compileSdk = AppInfo.Android.TargetSdk
}