plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.japaneseapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.japaneseapp"
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
                "proguard-rules.pro"
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
        compose = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(project(":coreUi"))
    implementation(project(":japanGame"))
    implementation(project(":japanCharacters"))
    implementation(project(":japanViewModel"))
    implementation(project(":japanTranslate"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation (libs.androidx.material)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.ui)
    implementation (libs.ui.tooling.preview)
    implementation (libs.androidx.lifecycle.runtime.ktx.v250)
    implementation (libs.androidx.activity.compose.v170)
    implementation (libs.androidx.compiler)
    implementation (libs.gson)
    implementation(libs.coil.compose)
    implementation(libs.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation (libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.robolectric)

    // Coroutines Test
    testImplementation(libs.kotlinx.coroutines.test)

    // Core Testing for InstantTaskExecutorRule
    testImplementation(libs.androidx.core.testing.v210)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material.icons.extended)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v261)
    implementation(libs.lottie.compose)
}