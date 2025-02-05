@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("catchytape.android.library")
    id("catchytape.android.hilt")
}

android {
    namespace = "com.ohdodok.catchytape.core.ui"

    defaultConfig {

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

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core:domain"))

    api(libs.material)

    api(libs.navigation.fragment.ktx)
    api(libs.navigation.ui.ktx)

    api(libs.glide)
    
}