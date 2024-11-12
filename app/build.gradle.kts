plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //Hilt
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    //Secrets Gradle Plugin
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 34

    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.weatherapp"
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
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    //Hilt
    implementation(libs.hilt.android.v251)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.play.services.location)
    ksp(libs.hilt.compiler)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose.v100)
    //Retrofit
    implementation(libs.retrofit)
    //GSON
    implementation(libs.converter.gson)
    //Lottie
    implementation(libs.lottie.compose)
    //Data Store
    implementation(libs.androidx.datastore.preferences)
    //Location
    implementation(libs.accompanist.permissions)




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
}