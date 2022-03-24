import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

group = "me.administrator"
version = "1.0"

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    val api by configurations
    val implementation by configurations
    val testImplementation by configurations

    dependencies {
        testImplementation(kotlin("test"))
        implementation("org.slf4j:slf4j-api:1.7.36")
        implementation("org.slf4j:slf4j-simple:1.7.36")
    }

    tasks {
        "test"(Test::class) {
            useJUnitPlatform()
            testLogging {
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                events(org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED, org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
                showExceptions = true
                showCauses = true
                showStackTraces = true
            }
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}