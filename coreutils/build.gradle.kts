plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
    id("signing")
}

group = "io.github.gowthambharathn"
version = "3.3.1"

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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = false
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            consumerProguardFiles("consumer-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
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

    implementation("com.google.android.gms:play-services-auth:21.5.0")
    implementation("androidx.activity:activity-ktx:1.9.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {

    publications {

        register<MavenPublication>("release") {

            groupId = project.group.toString()
            artifactId = "coreutils"
            version = project.version.toString()

            afterEvaluate {
                from(components["release"])
            }

            pom {

                name.set("CoreUtils")
                description.set("A modern Android utility library for Jetpack Compose.")
                url.set("https://github.com/gowthambharathn/CoreUtils")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("gowthambharathn")
                        name.set("Gowtham Bharath")
                        email.set("barathinfo28@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/gowthambharathn/CoreUtils.git")
                    developerConnection.set("scm:git:ssh://git@github.com/gowthambharathn/CoreUtils.git")
                    url.set("https://github.com/gowthambharathn/CoreUtils")
                }
            }
        }
    }

    repositories {
        mavenLocal()
    }
}

signing {
    sign(publishing.publications)
}