plugins {
    kotlin("jvm")
    application
}
kotlin { jvmToolchain(17) }
application {
    mainClass.set("example.ExampleKt")
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}
dependencies { implementation(project(":sdk")) }
