plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.point.sprind"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.point.sprind"
        minSdk = 29
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
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //MvpMoxy
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.ktx)
    implementation(libs.moxy)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.recyclerview)
    implementation(libs.moxy.material)
    kapt(libs.moxy.compiler)

    //Dagger2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //Coil
    implementation(libs.coil)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Retrofit
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson.v250)
    implementation(libs.adapter.rxjava3)

    //RxJava3
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    //Yandex maps
    implementation ("com.yandex.android:maps.mobile:4.6.1-lite")

    //Modules
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation ("com.google.android.material:material:1.6.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}