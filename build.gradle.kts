plugins {
    application
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(libs.guava)
    compileOnly("io.papermc.paper:paper-api:1.21.11-rc3-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.schoperation.RandomServerIcon"
}
