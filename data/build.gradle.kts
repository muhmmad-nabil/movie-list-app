plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.protobuf") version "0.9.4" apply true
}

android {
    namespace = "com.movielist.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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

        forEach {
            it.buildConfigField(
                "String",
                "ACCESS_TOKEN",
                "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYjljMTU2YjQ1ZGIxOWZjODEwN2M1NmRhYTI0NzkyNCIsIm5iZiI6MTcyNDk2ODA0Mi44OTY2MTcsInN1YiI6IjYyZjNjYjI1MGI1ZmQ2MDA3YzUyNmU2MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RpQW5fUea7cwY8Z0wkfmPpqb16vt-g8_3s1BIwLkl3o\""
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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.26.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}