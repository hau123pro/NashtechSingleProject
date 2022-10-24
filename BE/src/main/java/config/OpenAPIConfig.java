package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
		        .components(
		            new Components()
		                .addSecuritySchemes(securitySchemeName,
		                    new SecurityScheme()
		                        .name(securitySchemeName)
		                        .type(SecurityScheme.Type.HTTP)
		                        .scheme("bearer")
		                        .bearerFormat("JWT")
		                )
		        )
				.info(new Info()
						.title("Coza Store API")
						.description("Bookstore")
						.version("v0.0.1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation());
				
	}
}