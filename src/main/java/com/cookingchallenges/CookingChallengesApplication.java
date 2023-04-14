package com.cookingchallenges;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

// http://localhost:8080/swagger-ui/index.html
@SpringBootApplication
public class CookingChallengesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookingChallengesApplication.class, args);
    }

    @Configuration
    @OpenAPIDefinition(
            info =@Info(
                    title = "CulinaryChallenges API",
                    version = "${api.version}",
                    termsOfService = "${tos.uri}",
                    description = "API for CulinaryChallenges app by PS"
            )
    )
    public class OpenAPISecurityConfiguration {}

    @Configuration
    @SecurityScheme(
            name = "Bearer Authentication",
            type = SecuritySchemeType.HTTP,
            bearerFormat = "JWT",
            scheme = "bearer"
    )
    public class OpenAPI30Configuration {}

}
