import com.vanniktech.maven.publish.SonatypeHost

plugins {
    `java-library`
    idea
    signing

    id("com.vanniktech.maven.publish") version "0.31.0"
}

group = "one.tranic"
version = "1.0.4"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.viaversion.com")
    maven("https://repo.opencollab.dev/main/")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly("org.geysermc.geyser:api:2.4.2-SNAPSHOT")
    compileOnly("org.geysermc.floodgate:api:2.2.3-SNAPSHOT")

    compileOnly("com.viaversion:viaversion-api:5.2.2-SNAPSHOT")

    implementation("one.tranic:t-base:1.3.2")
    implementation("one.tranic:t-utils:1.3.0")
}

val targetJavaVersion = 17

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
    options.release = targetJavaVersion
}

tasks.withType<ProcessResources> {
    filteringCharset = Charsets.UTF_8.name()
}

val apiAndDocs: Configuration by configurations.creating {
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named(DocsType.SOURCES))
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    }
}

configurations.api {
    extendsFrom(apiAndDocs)
}

mavenPublishing {
    coordinates(group as String, "t-velocity", version as String)

    pom {
        name.set("TLIB Velocity")
        description.set("Basic Development Library")
        inceptionYear.set("2025")
        url.set("https://github.com/404Setup/t-velocity")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("404")
                name.set("404Setup")
                url.set("https://github.com/404Setup")
            }
        }
        scm {
            url.set("https://github.com/404Setup/t-velocity")
            connection.set("scm:git:git://github.com/404Setup/t-velocity.git")
            developerConnection.set("scm:git:ssh://git@github.com/404Setup/t-velocity.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
