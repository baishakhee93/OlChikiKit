# OlChikiKit — Phase 2 KMP SDK

**Author:** Baishakhee · **Repository:** https://github.com/baishakhee93/OlChikiKit  
**Namespace:** `io.github.baishakhee93.olchikikit` · **License:** Apache-2.0

Standalone **Kotlin Multiplatform** language SDK, separate from the ThinkIpySoft installable keyboard app (`com.thinkipysoft.olchickikit`).

## Modules

- `sdk`: Unicode Ol Chiki block helpers, data-driven Ol Chiki/English keyboard layouts, experimental Roman transliteration, offline dictionary, JSON data loader (`DictionaryLoader`), ranked prefix suggestions.
- `examples:jvm`: runnable JVM example.
- `sdk/src/commonTest`: portable Kotlin unit tests.

## Platforms

Android, JVM, iOS (arm64, simulator arm64/x64), and JavaScript (browser/node).

## Prerequisites

JDK 17, Android SDK 35, Gradle 8.9.

## Build and test

```sh
./gradlew :sdk:jvmTest
./gradlew :examples:jvm:run
./gradlew :sdk:assemble
```

## Usage

```kotlin
import io.github.baishakhee93.olchikikit.*

val isOlChiki = OlChikiKit.containsOlChiki("ᱡᱚᱦᱟᱨ")
val text = OlChikiKit.transliterate("johar")
val entries = DictionaryLoader.parseJson(jsonString)
val dictionary = OfflineDictionary(entries)
val candidates = OlChikiKit.suggestions("ᱡᱚ", dictionary)
```

## Maven publishing

Configured using Vanniktech Maven Publish plugin (`com.vanniktech.maven.publish`) with coordinates `io.github.baishakhee93:olchikikit-core:0.1.0`. See [docs/PUBLISHING.md](docs/PUBLISHING.md) for publishing workflows.
