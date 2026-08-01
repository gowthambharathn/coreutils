# 🚀 CoreUtils

**CoreUtils** is a modern Android utility library built with **Kotlin** and **Jetpack Compose** that provides reusable UI components, database utilities, and helper functions to accelerate Android app development.

Instead of rewriting common code for every project, simply include **CoreUtils** and start building.

---

## ✨ Features

### 🎨 Modern UI Components

* QuantumTextField
* NovaTextField
* Custom Buttons
* Cards
* Background Components
* Dialogs
* Loading Indicators
* Theme Helpers
* Reusable Compose Components

### 🗄️ Database Utilities

* SQLite Helper Functions
* CRUD Operations
* Database Connection Management
* Query Utilities
* Data Retrieval Helpers

### 🛠️ Utility Functions

* Validation Helpers
* Logging Utilities
* Package Information Helpers
* Date & Time Utilities
* Kotlin Extension Functions
* Navigation Helpers
* Toast & Snackbar Helpers
* File Utilities

---

## 📦 Installation

### Step 1

Add JitPack to your project's repositories.

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

---

### Step 2

Add the dependency.

```kotlin
dependencies {
    implementation("com.github.gowthambharathn:coreutils:<VERSION>")
}
```

## 🚀 Quick Example

```kotlin
QuantumTextField(
    value = username,
    onValueChange = {
        username = it
    }
)
```

Example button:

```kotlin
QuantumButton(
    text = "Login",
    onClick = {
        // Handle click
    }
)
```

---

---

## 💡 Why CoreUtils?

* Reusable architecture
* Cleaner codebase
* Faster development
* Consistent UI across projects
* Easy integration
* Modular design
* Lightweight
* Built using Kotlin and Jetpack Compose

---

## 📖 Requirements

* Android Studio
* Kotlin
* Jetpack Compose
* Minimum SDK: *(Update according to your project)*
* Compile SDK: *(Update according to your project)*

---

## 🤝 Contributing

Contributions, feature requests, and bug reports are welcome.

Feel free to open an issue or submit a pull request.

---

## ⭐ Support

If this library helps you, consider giving the repository a **⭐ Star** on GitHub.

---

## 📄 License

This project is licensed under the MIT License unless stated otherwise.

---

## 👨‍💻 Author

**Gowtham Bharath N**

Building reusable Android libraries and modern Jetpack Compose components to simplify Android development.
