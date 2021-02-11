plugins {
    id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")

    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"

    `maven-publish`
}

version = "1.0.0-SNAPSHOT"
group = "community.fabricmc.ssg"

repositories {
    jcenter()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.16.0-RC1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")

    implementation("com.charleskorn.kaml:kaml:0.27.0")
    implementation("com.github.slugify:slugify:2.4")
    implementation("io.pebbletemplates:pebble:3.1.4")
    implementation("com.vladsch.flexmark:flexmark-all:0.62.2")
}

detekt {
    config = files("detekt.yml")
}

kotlin {
    explicitApi()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"

    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.contracts.ExperimentalContracts"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "1.8"
}

publishing {
    repositories {
        maven {
            name = "KotDis"

            url = if (project.version.toString().contains("SNAPSHOT")) {
                uri("https://maven.kotlindiscord.com/repository/maven-snapshots/")
            } else {
                uri("https://maven.kotlindiscord.com/repository/maven-releases/")
            }

            credentials {
                username = project.findProperty("kotdis.user") as String?
                    ?: System.getenv("KOTLIN_DISCORD_USER")

                password = project.findProperty("kotdis.password") as String?
                    ?: System.getenv("KOTLIN_DISCORD_PASSWORD")
            }

            version = project.version
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components.getByName("java"))
        }
    }
}
