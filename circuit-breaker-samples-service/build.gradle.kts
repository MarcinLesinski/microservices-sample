import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
////        import("org.springframework.cloud:spring-cloud-dependencies:2021.0.1")
//        //Hoxton.SR10
//        mavenBom("org.springframework.cloud:spring-cloud-starter-parent:2021.0.1")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.1")
    }
}

dependencies {
    implementation(project(":numbers-provider"))
//    implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2021.0.1"))
//    implementation(enforcedPlatform("org.springframework.cloud:spring-cloud-dependencies:2021.0.1"))


    implementation("org.springframework.boot:spring-boot-starter-web:2.6.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.6.6")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.1")

    implementation("org.springframework.boot:spring-boot-starter-actuator:2.6.7")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard:2.2.10.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.1")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
