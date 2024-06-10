@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.yara.feature_login"
    compileSdk = rootProject.extra["compileAndroidSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minAndroidSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = rootProject.extra["javaVersion"] as JavaVersion
        targetCompatibility = rootProject.extra["javaVersion"] as JavaVersion
    }
    kotlinOptions {
        jvmTarget = rootProject.extra["kotlinVersion"] as String
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["composeVersion"] as String
    }
}

dependencies {
    // module
    implementation(project(":core"))

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)

    // compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    androidTestImplementation(composeBom)

    // lifecycle
    implementation(libs.androidx.legacy.support)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)

    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // rxjava
    implementation(libs.rxjava3)
    implementation(libs.rxjava3.rxandroid)
    // rxbinding
    implementation(libs.rxbinding)
    implementation(libs.rxbinding.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}