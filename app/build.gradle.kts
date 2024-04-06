plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    //id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.kotlinbestpractice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlinbestpractice"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //implementation(libs.androidx.navigation.compose)
    //implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)



    // retrofit
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.okhttp3)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Dagger - Hilt
    /*implementation(libs.hilt.android)
    ksp (libs.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)*/
    //implementation(libs.androidx.hilt.navigation.compose)
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation(libs.androidx.lifecycle.runtime.compose)
    kapt ("com.google.dagger:dagger-compiler:2.48.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.48.1")

    // navigation
    //implementation(libs.androidx.navigation.compose)
    // for image rendering
    //implementation (libs.io.coil)

    /*test*/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}