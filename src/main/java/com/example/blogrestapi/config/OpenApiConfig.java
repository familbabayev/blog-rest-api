package com.example.blogrestapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Blog REST API",
                version = "v1.0.0",
                description = "Blog REST API using Spring Boot"
        )
)
public class OpenApiConfig {

}
