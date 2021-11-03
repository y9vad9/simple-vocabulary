plugins {
    id(Deps.Plugins.Configuration.Kotlin.Android.Library)
}

dependencies {
    implementation(project(Deps.Modules.Features.Storage.Core))
    implementation(Deps.Libs.Androidx.Room.Runtime)
    implementation(Deps.Libs.Androidx.Room.Compiler)
    implementation(Deps.Libs.Androidx.Room.Ktx)
}

android {
    compileSdk = AppInfo.Android.TargetSdk
}