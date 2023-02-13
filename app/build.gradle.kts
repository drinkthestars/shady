plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kapt)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    buildToolsVersion = libs.versions.build.tools.get()
    namespace = "com.goofy.goober.androidtemplate"

    defaultConfig {
        applicationId = "com.goofy.goober.androidtemplate"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "consumer-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    android.sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.google)

    kapt(libs.hilt.compiler)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.Experimental"
    }
}
