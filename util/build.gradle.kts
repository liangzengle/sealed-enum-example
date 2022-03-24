repositories {
   maven(url = "https://jitpack.io")
}

dependencies {
   implementation("io.github.classgraph:classgraph:4.8.141")
   implementation("com.github.livefront.sealed-enum:runtime:0.4.0")
   implementation("org.eclipse.collections:eclipse-collections:11.0.0")
   implementation(kotlin("reflect"))
}