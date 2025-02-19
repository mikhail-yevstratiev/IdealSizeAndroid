# Overview
Simple Android SDK that prompts users to input height and weight and then recommends an ideal size.

# Requirements

Android SDK 25+

# Installation

1. Authenticate to GitHub Packages. For more information, see [Authenticating to GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages).
2. Add the package dependencies to your build.gradle file (Gradle Groovy) or build.gradle.kts file (Kotlin DSL) file.

Gradle Groovy:
```groovy
dependencies {
    implementation 'com.myevstratiev.ideal-size-android:1.0.0'
}
```

Kotlin DSL:
```groovy
dependencies {
    implementation("com.myevstratiev.ideal-size-android:1.0.0")
}
```

Add the repository to your build.gradle file (Gradle Groovy) or build.gradle.kts file (Kotlin DSL) file.

Gradle Groovy:
```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/mikhail-yevstratiev/IdealSizeAndroid")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
   }
}
```

Kotlin DSL:
```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/mikhail-yevstratiev/IdealSizeAndroid")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}
```

Run ./gradlew build to sync and build the project.

# Usage

There is a [sample Android project](./example) that demonstrates the usage of this plugin.

### Calculate perfect size from known Body Mass Index

```kotlin
import com.example.idealsizesdk.IdealSize
import com.example.idealsizesdk.SizeEnum

try {
    val size = IdealSize.sizeByBMI(25.0f)
    println("Recommended size: $size")
} catch (e: InvalidBMIError) {
    println("BMI value is out of range.")
}
```

### Show Recommendations View

In your MainActivity, you can launch the IdealSize InputActivity as a separate screen:

```kotlin
// other imports
import com.example.idealsizesdk.InputActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdealSizeAndroidSDKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Button(onClick = {
                            // Start InputActivity
                            startActivity(Intent(this@MainActivity, InputActivity::class.java))
                        }) {
                            Text("Find Your Perfect Fit")
                        }
                    }
                }
            }
        }
    }
}
```

# Licence

MIT. See [License](./LICENSE)