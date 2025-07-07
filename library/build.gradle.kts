import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

group = "com.github.tusharhow"  // Replace with your GitHub username
version = "1.0.0"

kotlin {
    // JVM target for desktop
    jvm()
    
    // Android target
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    // iOS targets
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    // Desktop targets
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()
    
    // Web targets
    js(IR) {
        browser()
        nodejs()
    }
    
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "io.github.kmpcounter"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "kmpcounter"
            version = project.version.toString()
            
            pom {
                name.set("KMPCounter")
                description.set("A Kotlin Multiplatform library for formatting numbers in human-readable format (1K, 1.2M, etc.)")
                url.set("https://github.com/tusharhow/KMPCounter")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("tusharhow")
                        name.set("Tushar Mahmud")
                        url.set("https://github.com/tusharhow")
                    }
                }
                
                scm {
                    url.set("https://github.com/tusharhow/KMPCounter")
                    connection.set("scm:git:git://github.com/tusharhow/KMPCounter.git")
                    developerConnection.set("scm:git:ssh://github.com:tusharhow/KMPCounter.git")
                }
            }
        }
    }
}
