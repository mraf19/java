package com.shirodev.shirobank.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Shiro Bank App",
                version = "1.0",
                description = "Shiro Bank App API",
                contact = @Contact(
                        name = "Shiro Dev",
                        url = "https://enigmacamp.com"
                )
        )
)
public class OpenApiConfiguration {
}