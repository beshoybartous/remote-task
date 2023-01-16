buildscript {
    allprojects {
        extra.apply {
            set("compose_version", "1.3.1")
            set("hilt_version", "2.38.1")
            set("coroutines_version", "1.6.4")
            set("room_version","2.4.3")
        }
    }

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}