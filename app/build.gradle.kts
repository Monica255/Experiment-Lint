plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinter)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.glide)
}

kotlinter {
    ktlintVersion = "1.5.0"
    ignoreFormatFailures = true
    ignoreLintFailures = false
    reporters = arrayOf("html")
}

tasks.register("enablePreCommitCheck") { // custom task named enablePreCommitCheck
    group = "git hooks" // this task belongs to git hook
    description = "Installs the pre-commit hook for ktlint and detekt checks" // adding description

    doLast {
        // Get the source and destination files
        val sourceFile = project.rootProject.file("script/pre-commit.sh")
        val hooksDir = project.rootProject.file(".git/hooks")
        val targetFile = hooksDir.resolve("pre-commit")

        // Create hooks directory if it doesn't exist
        hooksDir.mkdirs()

        // Copy the file
        if (sourceFile.exists()) {
            sourceFile.copyTo(targetFile, overwrite = true)

            // Make it executable
            targetFile.setExecutable(true)

            println("Pre-commit hook installed successfully!")
            println("Location: ${targetFile.absolutePath}")
        } else {
            throw GradleException("Source file not found: ${sourceFile.absolutePath}")
        }
    }
}

