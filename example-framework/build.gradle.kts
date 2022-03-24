plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    api("com.github.livefront.sealed-enum:runtime:0.4.0")
    ksp("com.github.livefront.sealed-enum:ksp:0.4.0")

    api(project(":util"))
}