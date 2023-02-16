enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "shady"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(":app")
include(":shaders")
include(":sketch")
include(":style")
