package com.soltel.elex.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
public OpenAPI personalizarSwagger() {
    return new OpenAPI()
        .info(new Info()
            .title("Endpoints de Elex")
            .description("Documentación con los Endpoints de Elex")
            .version("1.0")
            .contact(new Contact()
                .name("Juan José Mata Huertas")
                .url("https://sistemaelex.com/")
                .email("juanjosematahuertas@gmail.com")))
        .components(new Components()
            .addSecuritySchemes("basicAuth", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")))
        .servers(Arrays.asList(new Server()
            .url("http://localhost:8100")
            .description("Examen Final")));
}
}