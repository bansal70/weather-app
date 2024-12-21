plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt.android)
}


android {
  namespace = "com.weatherapp"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.weatherapp"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    val weatherApiKey = System.getenv("WEATHER_API_KEY") ?: "c6222fb764964bcb9f675600242012"
    buildConfigField("String", "WEATHER_API_KEY", "\"$weatherApiKey\"")

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.viewmodel)
  implementation(libs.androidx.lifecycle.runtime.ktx)

  // compose
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)

  // datastore
  implementation(libs.datastore)
  implementation(libs.datastore.preferences)

  // coroutines
  implementation(libs.coroutines)
  implementation(libs.coroutines.android)
  implementation(libs.coroutines.play.services)

  // Retrofit for API
  implementation(libs.retrofit)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.moshi)
  implementation(libs.okhttp)
  implementation(libs.okhttp.logging.interceptor)

  // Paging 3
  implementation(libs.paging)

  // Room Database
  implementation(libs.room.ktx)
  implementation(libs.room.runtime)
  ksp(libs.room.compiler)
  implementation(libs.room.paging)

  // hilt
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)

  // Image Loading
  implementation(libs.coil.compose)
  implementation(libs.coil.network)

  // Other
  implementation(libs.timber)


  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}