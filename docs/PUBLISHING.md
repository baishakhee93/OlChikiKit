# Maven Central publishing

1. Create/verify Maven Central publisher namespace `io.github.baishakhee93` (subject to Central verification).
2. Configure Maven Central portal user token and GPG signing keys securely; do not commit credentials.
3. Supply Gradle properties through environment variables or user-level `~/.gradle/gradle.properties` as documented by the Vanniktech plugin.
4. Test `gradle :sdk:publishToMavenLocal` and consume the local artifact from the separate ThinkIpySoft app.
5. Set a release version (remove `-SNAPSHOT`) and execute `gradle :sdk:publishAndReleaseToMavenCentral` once credentials, signing, and publication metadata are verified.
6. Create a matching GitHub tag and release notes.

Planned dependency (only after publication):

```kotlin
implementation("io.github.baishakhee93:olchikikit-core:0.1.0")
```

For development without publication, use a Gradle composite build (`includeBuild("../OlChikiKit")`) or `mavenLocal()` after local publishing, taking care to match actual artifact variants.
