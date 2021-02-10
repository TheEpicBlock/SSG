plugins {
    id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")

    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
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

    implementation("com.charleskorn.kaml:kaml:0.27.0")
    implementation("io.pebbletemplates:pebble:3.1.4")
    implementation("com.vladsch.flexmark:flexmark-all:0.62.2")
}

detekt {
    config = files("detekt.yml")
}

kotlin {
    explicitApi()
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "1.8"
}
