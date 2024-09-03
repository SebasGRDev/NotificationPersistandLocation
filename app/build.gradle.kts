plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("androidx.room") version "2.6.1" apply false
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sebasgrdev.ejerciciocomplementario"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sebasgrdev.ejerciciocomplementario"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.material.v180)


    //Corrutinas
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Localización
    implementation (libs.play.services.location)
    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    // Cloud Messaging
    implementation(libs.firebase.messaging.ktx)
    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler.v261)
    kapt(libs.androidx.room.compiler.v261)

    //RecyclerView
    implementation(libs.androidx.recyclerview)
    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}