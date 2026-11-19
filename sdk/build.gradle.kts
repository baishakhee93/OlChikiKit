plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.vanniktech.maven.publish")
}

group = "io.github.baishakhee93"
version = "0.1.0"

kotlin {
    androidTarget { publishLibraryVariants("release") }
    jvm()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    js(IR) { browser(); nodejs() }
    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "io.github.baishakhee93.olchikikit"
    compileSdk = 35
    defaultConfig { minSdk = 23 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = false)
    if (project.hasProperty("signing.keyId") || project.hasProperty("ORG_GRADLE_PROJECT_signingKeyId")) {
        signAllPublications()
    }
    coordinates("io.github.baishakhee93", "olchikikit-core", version.toString())
    pom {
        name.set("OlChikiKit")
        description.set("Offline Kotlin Multiplatform Ol Chiki Unicode, keyboard layouts, transliteration and suggestions SDK")
        url.set("https://github.com/baishakhee93/OlChikiKit")
        licenses { license { name.set("Apache License, Version 2.0"); url.set("https://www.apache.org/licenses/LICENSE-2.0") } }
        developers { developer { id.set("baishakhee93"); name.set("Baishakhee") } }
        scm { url.set("https://github.com/baishakhee93/OlChikiKit"); connection.set("scm:git:https://github.com/baishakhee93/OlChikiKit.git") }
    }
}
