plugins {
    id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")
    id("org.jetbrains.kotlin.jvm") version "1.4.30"

    application
}

repositories {
    jcenter()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.16.0-RC1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

application {
    mainClass.set("community.fabricmc.ssg.AppKt")
}

detekt {
    config = files("detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "1.8"
}
