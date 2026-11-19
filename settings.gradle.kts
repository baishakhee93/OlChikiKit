pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://nodejs.org/dist/") }
    }
}
rootProject.name = "OlChikiKit"
include(":sdk", ":examples:jvm")
