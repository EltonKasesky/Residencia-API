package org.serratec.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("contato@meudominio.com.br");
        contact.setName("Fulano");
        contact.setUrl("https://www.meudominio.com.br");

        License apacheLicense = new License().name("Apache License")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info().title("API projeto-curso Residencia")
                .version("1.0")
                .contact(contact)
                .description("API para o projeto final de aula da residencia")
                .termsOfService("https://www.meudominio.com.br/termos")
                .license(apacheLicense);

        return new OpenAPI().info(info);
    }
}

