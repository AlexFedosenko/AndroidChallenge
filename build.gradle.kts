buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradle)
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlin.Experimental"
            )
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}