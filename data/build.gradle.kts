plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "ru.point.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //DataStore
    implementation("androidx.datastore:datastore:1.1.1")
    implementation("androidx.datastore:datastore-rxjava3:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    //Dagger2
    val dagger2Version = "2.51"
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //Retrofit
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava3)

    //RxJava3
    implementation(libs.rxjava)


    //Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-rxjava3:$room_version")

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}