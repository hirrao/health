plugins {
    java
    application
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
}

group = "com.hirrao"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.jjwt.api)
    implementation(libs.jjwt.impl)
    implementation(libs.jjwt.jackson)
    implementation(libs.druid.spring.boot.starter)
    implementation(libs.mybatis.plus.starter)
    implementation(libs.mybatis.plus.jsqlparser)
    implementation(libs.mapstruct)

    testImplementation(libs.mybatis.plus.starter.test)
    testImplementation(libs.spring.boot.starter.test)

    compileOnly(libs.lombok)

    runtimeOnly(libs.mysql.connector)

    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct.processor)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainClass = "com.hirrao.health.HealthApplication"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
