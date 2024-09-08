
---

# **JWT and OAuth2 Implementation in Java Spring Boot using Gradle.kts and JWT 0.12.5**

## **Table of Contents**
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Project Setup](#project-setup)
4. [Gradle Configuration](#gradle-configuration)
5. [Spring Security Configuration](#spring-security-configuration)
6. [JWT Token Creation](#jwt-token-creation)
7. [OAuth2 Integration](#oauth2-integration)
8. [Controller and Securing Endpoints](#controller-and-securing-endpoints)
9. [Testing the Implementation](#testing-the-implementation)
10. [Conclusion](#conclusion)

---

## **Introduction**

This document explains how to implement **JWT (JSON Web Token)** authentication and **OAuth2** authorization in a Spring Boot application. We use **Gradle.kts** as the build tool and integrate **JWT version 0.12.5** for token handling. OAuth2 will be used to secure resources and provide an access delegation mechanism.

---

## **Prerequisites**

- Java 11 or higher installed.
- Spring Boot 2.7.x or later.
- Knowledge of Spring Security and OAuth2.
- Basic understanding of JWT.

---

## **Project Setup**

1. Create a new Spring Boot project.
    - Use [https://start.spring.io/](https://start.spring.io/) to generate the project or create it manually.
    - Add dependencies for `Spring Web`, `Spring Security`, `OAuth2`, `JWT`, and `JPA` if needed.

2. Initialize the project with Gradle Kotlin DSL (Gradle.kts).

---

## **Gradle Configuration**

Open the `build.gradle.kts` file and configure it for JWT and OAuth2.

``kotlin
plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")  // For JSON serialization
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
``

### Key Libraries:
- **jjwt-api**: Provides the JWT generation and validation.
- **oauth2-resource-server**: Enables OAuth2 support in Spring Security.

---

## **Spring Security Configuration**

Create a `SecurityConfig` class to configure Spring Security for JWT and OAuth2.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(getPublicKey()).build();
    }

    private RSAPublicKey getPublicKey() {
        // Load your RSA Public Key for validating the token
        return (RSAPublicKey) publicKey;
    }
}
```

### Explanation:
- The `oauth2ResourceServer()` configures the app to use OAuth2 with JWT.
- `JwtDecoder` is responsible for decoding and verifying JWT tokens.

---

## **JWT Token Creation**

Create a `JwtTokenProvider` class to handle the creation and validation of JWT tokens.

```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "secretKey"; // Replace with a stronger key
    private final long jwtExpirationMs = 86400000; // 24 hours

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
```

### Key Features:
- **generateToken**: Creates a JWT token for the user.
- **validateToken**: Validates the provided JWT token.
- **getUserNameFromJwtToken**: Extracts the username from the token.

---

## **OAuth2 Integration**

For OAuth2 integration, ensure that the OAuth2 client is properly configured in your `application.yml` or `application.properties`.

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://accounts.google.com  # Example issuer
```

### Explanation:
- This configuration allows Spring to retrieve and validate tokens from the specified OAuth2 provider (in this case, Google).

---

## **Controller and Securing Endpoints**

Create a controller with secured endpoints.

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a private endpoint accessible only with JWT.";
    }
}
```

### Explanation:
- The `/public` endpoint is accessible without a token.
- The `/private` endpoint is secured and requires a valid JWT.

---

## **Testing the Implementation**

1. **Generating JWT Tokens:**
    - Use a tool like Postman or curl to generate JWT tokens by hitting the authentication endpoints.

2. **Accessing Secured Endpoints:**
    - Send the JWT token in the `Authorization` header as a Bearer token to access secured endpoints.

```bash
curl -H "Authorization: Bearer <your-jwt-token>" http://localhost:8080/api/private
```

---

## **Conclusion**

This document demonstrates how to implement **JWT** and **OAuth2** in a Java Spring Boot application using **Gradle.kts
** and **JWT version 0.12.5**. By following these steps, you can ensure secure API access with OAuth2 and JWT
authentication, ready for integration with Google or any enterprise-level platform.

---

