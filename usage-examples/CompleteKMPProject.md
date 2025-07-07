# How Users Will Use KMPCounter in Their KMP Projects

## Complete Project Setup Example

### 1. Project Structure
```
MyKMPApp/
├── shared/
│   ├── src/
│   │   ├── commonMain/kotlin/
│   │   │   └── NumberFormatter.kt
│   │   ├── androidMain/kotlin/
│   │   ├── iosMain/kotlin/
│   │   └── commonTest/kotlin/
│   └── build.gradle.kts
├── androidApp/
├── iosApp/
├── desktopApp/
├── build.gradle.kts
└── settings.gradle.kts
```

### 2. Root build.gradle.kts
```kotlin
plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
}
```

### 3. settings.gradle.kts
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // Required for KMPCounter
    }
}

rootProject.name = "MyKMPApp"
include(":shared")
include(":androidApp")
include(":desktopApp")
```

### 4. shared/build.gradle.kts
```kotlin
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    jvm("desktop")
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    js(IR) {
        browser()
        nodejs()
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Add KMPCounter dependency
                implementation("com.github.tusharahmed:KMPCounter:1.0.0")
            }
        }
        
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.mycompany.myapp.shared"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 24
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
```

### 5. Usage in Common Code (shared/src/commonMain/kotlin/NumberFormatter.kt)

```kotlin
package com.mycompany.myapp.shared

import io.github.kmpcounter.KMPCounter
import io.github.kmpcounter.toHumanReadable

class NumberFormatter {
    
    // Static methods using the object directly
    fun formatFollowers(count: Long): String {
        return KMPCounter.format(count)
    }
    
    fun formatViews(count: Int): String {
        return KMPCounter.format(count, precision = 1)
    }
    
    fun formatMoney(amount: Double): String {
        return KMPCounter.format(amount, precision = 2, useFullWords = true)
    }
    
    // Extension function examples
    fun formatLikes(count: Int): String {
        return count.toHumanReadable()
    }
    
    fun formatShares(count: Long): String {
        return count.toHumanReadable(precision = 0)
    }
    
    // Real-world usage examples
    fun getSocialMediaStats(
        followers: Long,
        likes: Int,
        views: Long,
        revenue: Double
    ): SocialMediaStats {
        return SocialMediaStats(
            followers = followers.toHumanReadable(),
            likes = likes.toHumanReadable(),
            views = views.toHumanReadable(precision = 1),
            revenue = revenue.toHumanReadable(useFullWords = true)
        )
    }
}

data class SocialMediaStats(
    val followers: String,
    val likes: String,
    val views: String,
    val revenue: String
)

// Usage examples for different platforms
object PlatformExamples {
    
    fun getFormattedNumbers(): Map<String, String> {
        return mapOf(
            "followers" to 125000.toHumanReadable(),          // "125K"
            "views" to 2500000.toHumanReadable(),             // "2.5M"
            "revenue" to 1500000.0.toHumanReadable(useFullWords = true), // "1.5 million"
            "downloads" to KMPCounter.format(45000, precision = 0),      // "45K"
            "subscribers" to KMPCounter.format(890000, precision = 1),   // "890K"
        )
    }
}
```

### 6. Android Usage (androidApp)

```kotlin
// MainActivity.kt
class MainActivity : ComponentActivity() {
    private val numberFormatter = NumberFormatter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MyAppTheme {
                Column {
                    Text("Followers: ${numberFormatter.formatFollowers(125000)}")  // "125K"
                    Text("Views: ${numberFormatter.formatViews(2500000)}")         // "2.5M"
                    Text("Revenue: ${numberFormatter.formatMoney(1500000.0)}")     // "1.5 million"
                }
            }
        }
    }
}
```

### 7. iOS Usage (iosApp)

```swift
// ContentView.swift
import SwiftUI
import shared

struct ContentView: View {
    let numberFormatter = NumberFormatter()
    
    var body: some View {
        VStack {
            Text("Followers: \(numberFormatter.formatFollowers(count: 125000))")
            Text("Views: \(numberFormatter.formatViews(count: 2500000))")
            Text("Revenue: \(numberFormatter.formatMoney(amount: 1500000.0))")
        }
    }
}
```

### 8. Desktop Usage (desktopApp)

```kotlin
// Main.kt
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mycompany.myapp.shared.NumberFormatter

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
@Preview
fun App() {
    val numberFormatter = NumberFormatter()
    
    Column {
        Text("Followers: ${numberFormatter.formatFollowers(125000)}")
        Text("Views: ${numberFormatter.formatViews(2500000)}")  
        Text("Revenue: ${numberFormatter.formatMoney(1500000.0)}")
    }
}
```

### 9. Web/JS Usage

```kotlin
// Main.kt for JS target
import io.github.kmpcounter.toHumanReadable
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        val container = document.getElementById("root")
        container?.innerHTML = """
            <h1>Social Media Stats</h1>
            <p>Followers: ${125000.toHumanReadable()}</p>
            <p>Views: ${2500000.toHumanReadable()}</p>
            <p>Likes: ${89000.toHumanReadable()}</p>
        """.trimIndent()
    }
}
```

## Benefits for Users

1. **Single Dependency**: Just add one line to `commonMain` dependencies
2. **All Platforms**: Works on Android, iOS, Desktop, Web automatically
3. **Type Safe**: Full Kotlin type safety across all platforms
4. **Easy to Use**: Simple API with extension functions
5. **Customizable**: Precision control and full words mode
6. **No Platform-Specific Code**: Write once, use everywhere

## Version Management

Users can specify different versions:
- Latest: `com.github.tusharahmed:KMPCounter:latest`
- Specific version: `com.github.tusharahmed:KMPCounter:1.0.0`
- Commit hash: `com.github.tusharahmed:KMPCounter:commit-hash`
- Branch: `com.github.tusharahmed:KMPCounter:branch-name` 