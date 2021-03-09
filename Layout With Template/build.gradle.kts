import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com.hello"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}
/*
bootRun {
    jvmArgs= "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=*:5005"
}*/

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("org.apache.logging.log4j:log4j-api")
    implementation ("org.springframework.data:spring-data-commons")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.hibernate.validator:hibernate-validator")
}

tasks.withType<Test> {
    useJUnitPlatform()
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
