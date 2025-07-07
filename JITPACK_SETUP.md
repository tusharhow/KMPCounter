# 📦 Publishing KMPCounter to JitPack

## 🚀 Steps to Publish

### 1. Push to GitHub
```bash
# Initialize git repository (if not already done)
git init
git add .
git commit -m "Initial commit: KMPCounter library"

# Add your GitHub repository as remote
git remote add origin https://github.com/tusharahmed/KMPCounter.git
git push -u origin main
```

### 2. Create a Release on GitHub
1. Go to your GitHub repository: `https://github.com/tusharahmed/KMPCounter`
2. Click on "Releases" → "Create a new release"
3. Tag version: `1.0.0`
4. Release title: `KMPCounter v1.0.0`
5. Description: `First release of KMPCounter - A Kotlin Multiplatform library for formatting numbers in human-readable format`
6. Click "Publish release"

### 3. Test on JitPack
1. Visit: `https://jitpack.io/#tusharahmed/KMPCounter/1.0.0`
2. Click "Get it" to trigger the build
3. Wait for the build to complete (green checkmark)

## 📱 How Users Will Use Your Library

### Step 1: Add JitPack Repository
Users add this to their `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // For KMPCounter
    }
}
```

### Step 2: Add Dependency
In their KMP module's `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.tusharahmed:KMPCounter:1.0.0")
            }
        }
    }
}
```

### Step 3: Use in Code
```kotlin
import io.github.kmpcounter.KMPCounter
import io.github.kmpcounter.toHumanReadable

// Direct usage
val formatted = KMPCounter.format(125000) // "125K"

// Extension function
val followers = 1500000.toHumanReadable() // "1.5M"
```

## 🔄 Version Updates

To release a new version:
1. Update `version = "x.x.x"` in `library/build.gradle.kts`
2. Commit and push changes
3. Create a new GitHub release with the new version tag
4. JitPack will automatically build the new version

## ✅ Library Features for Users

- ✅ **Cross-platform**: Android, iOS, Desktop, Web
- ✅ **Easy integration**: Single dependency line
- ✅ **Type-safe**: Full Kotlin type safety
- ✅ **Flexible**: Precision control and full words mode
- ✅ **Extension functions**: Convenient `.toHumanReadable()`
- ✅ **No platform-specific code**: Write once, use everywhere

## 📊 Real-world Usage Examples

```kotlin
// Social media app
val followers = 125000.toHumanReadable()          // "125K"
val views = 2500000.toHumanReadable()             // "2.5M"

// Financial app
val revenue = 1500000.0.toHumanReadable(useFullWords = true) // "1.5 million"

// Gaming app
val score = 89500.toHumanReadable(precision = 0)  // "90K"

// E-commerce app
val sales = KMPCounter.format(750000, precision = 1) // "750K"
```

## 🎯 Benefits Over Alternatives

1. **Multiplatform**: Works across all KMP targets
2. **Lightweight**: Minimal dependencies
3. **Easy to use**: Simple API design
4. **Well-tested**: Comprehensive test suite
5. **Active maintenance**: Regular updates via GitHub releases
6. **Free hosting**: No Maven Central fees or complexity 