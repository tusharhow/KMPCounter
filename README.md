[![official project](http://jb.gg/badges/official.svg)](https://github.com/JetBrains#jetbrains-on-github)

# KMPCounter

[![JitPack](https://jitpack.io/v/tusharhow/KMPCounter.svg)](https://jitpack.io/#tusharhow/KMPCounter)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A Kotlin Multiplatform library for formatting numbers into human-readable format. Convert numbers like `1000` to `"1K"`, `1200000` to `"1.2M"`, and so on.

## Supported Platforms

- ✅ **Android**
- ✅ **iOS** (iosX64, iosArm64, iosSimulatorArm64)
- ✅ **Desktop** (JVM, Linux, macOS, Windows)
- ✅ **Web** (JavaScript, WebAssembly)

## Installation

### Step 1: Add JitPack repository

Add JitPack repository to your root `build.gradle.kts`:

```kotlin
allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // Add this line
    }
}
```

Or in `settings.gradle.kts` (for newer Gradle versions):

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // Add this line
    }
}
```

### Step 2: Add dependency

#### Gradle (Kotlin DSL)

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.tusharhow:KMPCounter:1.0.0")
            }
        }
    }
}
```

#### Gradle (Groovy DSL)

```groovy
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation 'com.github.tusharhow:KMPCounter:1.0.0'
            }
        }
    }
}
```

## Usage

### Basic Usage

```kotlin
import io.github.kmpcounter.KMPCounter

// Using the object directly
KMPCounter.format(1000)        // "1K"
KMPCounter.format(1500)        // "1.5K"
KMPCounter.format(1000000)     // "1M"
KMPCounter.format(1200000)     // "1.2M"
KMPCounter.format(1000000000)  // "1B"

// Using extension functions
1000.toHumanReadable()         // "1K"
1500L.toHumanReadable()        // "1.5K"
1000000.0.toHumanReadable()    // "1M"
```

### Precision Control

```kotlin
KMPCounter.format(1234, precision = 0)  // "1K"
KMPCounter.format(1234, precision = 1)  // "1.2K" (default)
KMPCounter.format(1234, precision = 2)  // "1.23K"
KMPCounter.format(1234, precision = 3)  // "1.234K"

// Using extension functions
1234.toHumanReadable(precision = 2)     // "1.23K"
```

### Full Words Mode

```kotlin
KMPCounter.format(1000, useFullWords = true)      // "1 thousand"
KMPCounter.format(1500000, useFullWords = true)   // "1.5 million"
KMPCounter.format(2000000000, useFullWords = true) // "2 billion"

// Using extension functions
1000.toHumanReadable(useFullWords = true)          // "1 thousand"
```

### Supported Number Types

The library supports all common number types:

```kotlin
// Int
KMPCounter.format(1000)
1000.toHumanReadable()

// Long
KMPCounter.format(1000L)
1000L.toHumanReadable()

// Double
KMPCounter.format(1000.0)
1000.0.toHumanReadable()

// Float
KMPCounter.format(1000f)
1000f.toHumanReadable()
```

### Negative Numbers

```kotlin
KMPCounter.format(-1000)    // "-1K"
KMPCounter.format(-1500)    // "-1.5K"
(-1000).toHumanReadable()   // "-1K"
```

## Examples

| Input | Output | Full Words |
|-------|--------|------------|
| 0 | "0" | "0" |
| 999 | "999" | "999" |
| 1000 | "1K" | "1 thousand" |
| 1500 | "1.5K" | "1.5 thousand" |
| 10000 | "10K" | "10 thousand" |
| 100000 | "100K" | "100 thousand" |
| 1000000 | "1M" | "1 million" |
| 1200000 | "1.2M" | "1.2 million" |
| 1000000000 | "1B" | "1 billion" |
| 1000000000000 | "1T" | "1 trillion" |

## Supported Suffixes

| Value | Short | Full Word |
|-------|-------|-----------|
| 10³ | K | thousand |
| 10⁶ | M | million |
| 10⁹ | B | billion |
| 10¹² | T | trillion |
| 10¹⁵ | P | quadrillion |
| 10¹⁸ | E | quintillion |
| 10²¹ | Z | sextillion |
| 10²⁴ | Y | septillion |

## Platform-Specific Usage

### Android

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val followers = 12500
        val formattedFollowers = followers.toHumanReadable() // "12.5K"
        
        textView.text = "$formattedFollowers followers"
    }
}
```

### iOS (SwiftUI)

```swift
import KMPCounter

struct ContentView: View {
    var body: some View {
        Text("Followers: \(KMPCounterKt.format(12500))")
    }
}
```

### Desktop (Compose)

```kotlin
@Composable
fun App() {
    val likes = 1250000
    Text("${likes.toHumanReadable()} likes")
}
```

### Web (React)

```kotlin
val App = FC<Props> {
    val views = 2500000
    div {
        +"Views: ${views.toHumanReadable()}" // "Views: 2.5M"
    }
}
```

## Building from Source

1. Clone the repository:
```bash
git clone https://github.com/kmpcounter/kmpcounter.git
cd kmpcounter
```

2. Build the project:
```bash
./gradlew build
```

3. Run tests:
```bash
./gradlew test
```

4. Publish to local repository:
```bash
./gradlew publishToMavenLocal
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Changelog

### 1.0.0
- Initial release
- Support for all major platforms (Android, iOS, Desktop, Web)
- Number formatting with customizable precision
- Full words mode
- Extension functions for all number types
- Comprehensive test coverage
