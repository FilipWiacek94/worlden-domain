plugins {
    id("buildsrc.convention.kotlin-jvm")
}

group = "com.worlden"
version = "unspecified"

tasks.named("bootJar") { enabled = false }
tasks.named("jar") { enabled = true }