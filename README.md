OlChikiKit
Open-source Kotlin Multiplatform toolkit for Santali (Ol Chiki) language support.
![Kotlin Multiplatform CI](https://github.com/baishakhee93/OlChikiKit/actions/workflows/ci.yml/badge.svg)
![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![GitHub](https://img.shields.io/badge/Source-GitHub-black?logo=github)
Repository: https://github.com/baishakhee93/OlChikiKit  
Maintainer: Baishakhee  
License: Apache License 2.0
> **Publication status:** The repository describes the SDK, but a publicly downloadable JitPack/Maven artifact has **not been verified**. The installation coordinates below are examples to use **only after** a successful JitPack build. Do not advertise a version as released until it exists. The public GitHub view checked on 2026-11-19 showed README and LICENSE; if you have pushed SDK files since then, verify they are visible on the `main` branch.
Contents
Overview
What developers can build
Feature scope
Supported platforms
Installation from JitPack
Android demo: step by step
How the integration works
Build from source
System-wide keyboard vs in-app keyboard
Troubleshooting
Publishing a release
Contributing
License
Overview
OlChikiKit aims to make it easier to add Santali language support written in the Ol Chiki script to applications. Instead of every developer implementing character handling, keyboard layouts, transliteration, and vocabulary support independently, a reusable SDK can provide shared building blocks.
Important: A library does not automatically display a keyboard on every phone. The consuming app chooses which features to display and how to display them.
What developers can build
Use case	How the SDK can help	What the app developer builds
Santali learning app	Characters, language content	Lessons, quizzes, progress screens
In-app Ol Chiki keyboard	Key layout data	Compose/Views keyboard UI and text input
Dictionary app	Offline vocabulary lookup	Search UI and content updates
Transliteration tool	Supported Roman-to-Ol-Chiki mappings	Input/output UI and language rules
Chat or notes app	Ol Chiki text handling	Messaging or notes features
System-wide Android keyboard	Reusable language data	`InputMethodService`, settings, input UI
Feature scope
The repository's stated SDK scope includes:
Ol Chiki Unicode utilities
Keyboard layouts
Transliteration
Offline dictionary data
Prefix/word suggestions
Language-learning data
Availability of individual APIs, classes, and datasets must be checked against the actual source and tests. This README deliberately does not invent class names or claim all features have been released.
Supported platforms
Platform	Integration approach	Verification needed
Android (Kotlin/Java)	Android-compatible published artifact	Confirm Android target and dependency resolution
JVM/desktop	JVM artifact	Confirm JVM target and public API
iOS	Kotlin/Native framework, typically via KMP build	Confirm framework export and Xcode integration
Web/JavaScript	Kotlin/JS build or JS package	Confirm JS target and package publication
Flutter	Flutter plugin/platform bridge wrapping native APIs	Requires a separate Flutter integration
JitPack Gradle dependencies are not a universal installation method for Flutter, iOS, or Web. Each platform needs its own appropriate integration or published artifact.
Installation from JitPack
1. Check the repository
Open OlChikiKit on GitHub. The branch you publish must contain build scripts, SDK source code, Gradle wrapper, and a working publishing configuration.
2. Build on JitPack
Open JitPack.
Enter `baishakhee93/OlChikiKit`.
Select an existing tag, such as `v0.1.0`, or a commit/branch snapshot.
Click Get it and wait for the build result.
Open the build log if the build fails.
Copy the exact artifact coordinates shown by JitPack. The module name can vary with the Gradle publishing setup.
A green GitHub Actions build does not automatically mean the library has been published to JitPack.
3. Add the JitPack Maven repository
In the consuming app's `settings.gradle.kts`, add JitPack to the existing `dependencyResolutionManagement.repositories` block:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```
Do not replace unrelated settings or duplicate the repository block.
4. Add the SDK dependency
In the consuming Android app's `app/build.gradle.kts`:
```kotlin
dependencies {
    // EXAMPLE ONLY — use the exact dependency shown by JitPack
    implementation("com.github.baishakhee93.OlChikiKit:sdk:v0.1.0")
}
```
This is a candidate multi-module coordinate, not a verified published package. It will not work until the tag exists, JitPack succeeds, and the artifact name matches.
5. Sync and build
In Android Studio, click Sync Project with Gradle Files, then run:
```powershell
.\gradlew.bat :app:assembleDebug
```
If Gradle cannot find the artifact, verify the JitPack build and its exact dependency snippet first.
Android demo: step by step
This simple demonstration works without the SDK. It shows how an Android application can render Ol Chiki characters and collect typed text. After publishing the SDK, replace the hard-coded character list with your verified public keyboard-layout API.
In Android Studio, create an Empty Activity with Kotlin + Jetpack Compose.
Name it `OlChikiKitDemo`; use package `com.thinkipysoft.olchikikitdemo`.
Use the project template's existing Compose/Material 3 dependencies.
Replace `MainActivity.kt` with:
```kotlin
package com.thinkipysoft.olchikikitdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { OlChikiDemo() } }
    }
}

@Composable
fun OlChikiDemo() {
    var text by remember { mutableStateOf("") }
    // Unicode Ol Chiki letters U+1C5A through U+1C77
    val letters = remember { (0x1C5A..0x1C77).map { String(Character.toChars(it)) } }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("OlChikiKit Demo", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Ol Chiki text") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )
        Spacer(Modifier.height(12.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(5), modifier = Modifier.weight(1f)) {
            items(letters) { letter ->
                TextButton(onClick = { text += letter }) { Text(letter) }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { text += " " }) { Text("Space") }
            Button(onClick = { text = text.dropLast(1) }) { Text("Delete") }
            Button(onClick = { text = "" }) { Text("Clear") }
        }
    }
}
```
Run the app on an emulator or physical Android device.
Tap the Ol Chiki letters; they appear in the text field. This demonstrates Unicode text input, not SDK integration.
After the SDK is published, add the JitPack dependency and replace the demo's hard-coded data with verified SDK calls. Consult the SDK source or API documentation for the real class names and signatures.
Expected screen
```text
+----------------------------------+
| OlChikiKit Demo                  |
|                                  |
| Ol Chiki text                    |
| +------------------------------+ |
| | ᱚᱞ ᱪᱤᱠᱤ                     | |
| +------------------------------+ |
|                                  |
| [ Ol Chiki character buttons ]   |
|                                  |
| [ Space ] [ Delete ] [ Clear ]   |
+----------------------------------+
```
Appearance depends on Android version, theme, and the device's font support.
How the integration works
```text
GitHub repository (SDK source)
            |
            v
    JitPack builds/publishes
            |
            v
   Gradle resolves dependency
            |
            v
  Developer imports public APIs
            |
            v
  Developer builds app interface
            |
            v
      User interacts with app
```
The SDK provides reusable language logic or data; it does not automatically create a finished application UI.
Build from source
Requirements
JDK 17
Android Studio for Android development
A compatible Android SDK for Android targets
Gradle 8.9 (or the version declared by the project's verified wrapper)
macOS and Xcode for iOS simulator testing
Clone
```bash
git clone https://github.com/baishakhee93/OlChikiKit.git
cd OlChikiKit
```
Test on Windows
```powershell
.\gradlew.bat --version
.\gradlew.bat :sdk:jvmTest
```
Test on macOS/Linux
```bash
./gradlew --version
./gradlew :sdk:jvmTest
```
If the example modules exist, you can also try:
```bash
./gradlew :examples:jvm:run
```
On a macOS runner with the necessary Kotlin/Native target and Xcode environment:
```bash
./gradlew :sdk:iosSimulatorArm64Test
```
Run only the tasks actually defined in the checked-out repository. To inspect them, use `./gradlew tasks --all` (or `gradlew.bat` on Windows). Never run an unverified wrapper JAR.
System-wide keyboard vs in-app keyboard
An in-app keyboard is a custom view within one application. A system-wide keyboard is a separate Android input method that the user installs and enables in system settings.
To ship a system-wide Ol Chiki keyboard, developers must implement an Android `InputMethodService`, declare the service in the manifest, build key input handling, support text editing and cursor behavior, and provide settings/onboarding. OlChikiKit's keyboard data can help, but the SDK dependency alone does not make a system keyboard available in WhatsApp or other apps.
The intended ThinkIpySoft installable keyboard application ID is `com.thinkipysoft.olchickikit` (separate from the demo app).
Troubleshooting
Problem	What to check
`Could not find ... OlChikiKit`	JitPack build status, exact coordinates, tag/commit, repository configuration
`GradleWrapperMain` not found	Replace broken wrapper with an official, verified Gradle wrapper
Wrapper validation fails in CI	Verify official wrapper JAR SHA-256; do not disable validation
`Task ... not found`	Check `settings.gradle.kts`, module names, and `tasks --all`
Ol Chiki letters appear as boxes	Device/system font support and Unicode rendering
iOS tests fail on Windows	Run iOS simulator tasks on macOS with Xcode
Flutter cannot import the Gradle dependency directly	Create/use a Flutter plugin or platform bridge
Publishing a release
Maintainers should:
Confirm SDK source and build scripts are committed to GitHub.
Run the relevant JVM/Android/iOS checks.
Confirm JitPack can publish the intended module artifact.
Create and push a Git tag, e.g. `v0.1.0`:
```bash
   git tag v0.1.0
   git push origin v0.1.0
   ```
Build that tag on JitPack.
Copy the actual dependency coordinates into this README.
Publish release notes and a tested sample application.
Do not create a release tag until the source and build configuration are ready.
Contributing
Contributions are welcome. To propose a change:
Open an issue describing the feature or bug.
Fork the repository and create a feature branch.
Make a focused change and add or update tests.
Run the applicable Gradle tests.
Open a pull request describing the change and how it was tested.
For language data and transliteration changes, include representative Santali examples and explain the intended spelling or mapping rules.
License
Licensed under the Apache License, Version 2.0. See LICENSE.
Links
Source code
Issues
GitHub Actions
JitPack
---
Maintained by Baishakhee. Contributions that improve Santali language tooling are welcome.
