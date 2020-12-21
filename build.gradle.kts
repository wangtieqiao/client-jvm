buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
//        val v = "1.3.61"
        val v = "1.4.21"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$v")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$v")
    }
}

plugins {
    val v = "1.3.61"
    `java-library`
    kotlin("jvm") version "$v"
    kotlin("plugin.serialization") version "$v"
    kotlin("kapt") version "$v"
    maven
}

group = "com.github.mmoghaddam385"

dependencies {
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")

//    val ktorVersion = "1.3.1"
    val ktorVersion = "1.4.0"
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")

    // Annotation processor that generates Java builders for data classes
//    val ktBuilderVersion = "1.1.0"
    val ktBuilderVersion = "1.2.1"
    implementation("com.thinkinglogic.builder:kotlin-builder-annotation:$ktBuilderVersion")
    kapt("com.thinkinglogic.builder:kotlin-builder-processor:$ktBuilderVersion")

    testImplementation("junit:junit:4.12")
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    val sourcesJar by creating(Jar::class) {
        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    artifacts {
        add("archives", sourcesJar)
        add("archives", jar)
    }
}