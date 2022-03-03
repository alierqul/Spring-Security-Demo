# Spring-Security Auth Demo Application + Reactjs
## Technologies
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security
- Swagger-UI
- JWT Token
- Lombok
- React
- Intellij Idea
- Gradle

## Description
Spring security configuration task and becoming authority with JWT token.

## Gradle Dependencies
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springdoc:springdoc-openapi-ui:1.6.6'
    implementation 'io.jsonwebtoken:jjwt:0.9.1'

    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'

## Aplications yml

    spring:
        h2:
            console:
                enabled: true
        datasource:
            url: jdbc:h2:mem:test

    # JWT Configurations [Secret env.]
    jwt:
        auth:
            app: Spring-Security-App
            secret_key: testkey#secret@12334
            expires_in: 3600