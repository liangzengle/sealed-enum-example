plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

dependencies{
    implementation(project(":example-framework"))
    implementation("com.github.livefront.sealed-enum:runtime:0.4.0")
    ksp("com.github.livefront.sealed-enum:ksp:0.4.0")
}