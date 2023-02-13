plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.hilt.android).apply(false)
    alias(libs.plugins.kapt).apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
