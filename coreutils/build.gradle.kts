plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

android {
    namespace = "infinity.developers.coreutils"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    // With Kotlin 2.x + plugin, you don't really need this,
    // but keeping it safe for AGP compatibility
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.animation:animation:1.6.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //signinwith google
    implementation("com.google.android.gms:play-services-auth:21.5.0")
    implementation("androidx.activity:activity-ktx:1.9.0")
}

afterEvaluate {
    extensions.configure<org.gradle.api.publish.PublishingExtension>("publishing") {
        publications {
            create<org.gradle.api.publish.maven.MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.gowthambharathn"
                artifactId = "coreutils"
                version = "3.3.1"
            }
        }
    }
}
