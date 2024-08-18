import org.jetbrains.kotlin.js.inline.util.aliasArgumentsIfNeeded

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("kotlin-kapt")
    alias(libs.plugins.ksp)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

android {
    namespace = "com.yara.android_practicum"
    compileSdk = rootProject.extra["compileAndroidSdk"] as Int

    defaultConfig {
        applicationId = "com.yara.android_practicum"
        minSdk = rootProject.extra["minAndroidSdk"] as Int
        targetSdk = rootProject.extra["targetAndroidSdk"] as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
}

dependencies {
    // module
    implementation(project(":core"))
    implementation(project(":feature:help"))
    implementation(project(":feature:login"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:news"))
    implementation(project(":feature:filter"))
    implementation(project(":feature:search"))

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)

    // work
    implementation(libs.androidx.work.runtime.ktx)

    // lifecycle
    implementation(libs.androidx.legacy.support)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)

    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // kotlinx-datetime
    implementation(libs.kotlinx.datetime)

    // glide
    implementation(libs.glide)

    // dagger
    implementation(libs.google.dagger)
    kapt(libs.google.dagger.compiler)

    // testing
    implementation(libs.androidx.fragment.testing)
    implementation(libs.androidx.test.core)

    // unit testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    // instrumented testing
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}