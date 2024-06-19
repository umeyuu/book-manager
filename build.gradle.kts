plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	id("com.thinkimi.gradle.MybatisGenerator") version "2.4"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.book.manager"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1") // 追加
	implementation("mysql:mysql-connector-java:8.0.23") // 追加
	mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0") // 追加

	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.springframework.session:spring-session-data-redis")
	implementation("redis.clients:jedis")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// 追加
mybatisGenerator {
	verbose = true
	configFile = "/Users/umedayuusuke/IdeaProjects/book-manager/src/main/resources/generatorConfig.xml"
}