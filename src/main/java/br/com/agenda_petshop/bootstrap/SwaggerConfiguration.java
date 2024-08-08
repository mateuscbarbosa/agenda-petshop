package br.com.agenda_petshop.bootstrap;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI swaggerConfiguration(){
        final String securityScheme = "bearerAuth";

        Contact contact = new Contact();
        contact.setEmail("mateuscbarbosa@gmail.com");
        contact.setName("Mateus Barbosa");

        Info info = new Info()
                .title("Agenda PetShop")
                .description("Aplicação de agenda para banho e tosa")
                .contact(contact)
                .version("0.0.1")
                .license(new License());

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList(securityScheme))
                .components(new Components()
                        .addSecuritySchemes(securityScheme, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));
    }
}
