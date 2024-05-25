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
    implementation(project(":feature_help"))

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)

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

    // rxjava
    implementation(libs.rxjava3)
    implementation(libs.rxjava3.rxandroid)
    // rxbinding
    implementation(libs.rxbinding)
    implementation(libs.rxbinding.appcompat)

    // glide
    implementation(libs.glide)

    // room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // dagger
    implementation(libs.google.dagger)
    kapt(libs.google.dagger.compiler)

    // test
    testImplementation(libs.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}