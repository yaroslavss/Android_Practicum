// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val compileAndroidSdk by extra(34)
    val targetAndroidSdk by extra(33)
    val minAndroidSdk by extra(26)

    val javaVersion by extra(JavaVersion.VERSION_17)
    val kotlinVersion by extra("17")

    val composeVersion by extra("1.5.7")
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.com.android.library) apply false
}
//Workaround for "Expecting an expression" build error
println("")