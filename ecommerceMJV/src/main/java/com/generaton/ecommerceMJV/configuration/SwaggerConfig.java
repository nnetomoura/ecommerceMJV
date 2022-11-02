package com.generaton.ecommerceMJV.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;



@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springEcommerceMjvOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("E-commerce MJV")
					.description("Projeto back-end e-commerce - MJV School - Claudio Pereira")
					.version("v0.0.1")
				.license(new License()
					.name("MJV")
					.url("https://www.mjvinnovation.com/pt-br//"))
				.contact(new Contact()
					.name("Back-end e-commerce MJV")
					.url("https://github.com/nnetomoura")
					.email("claudio.prepara0@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Linkedin")
					.url("https://www.linkedin.com/in/claudiomourapereira/"));
	}
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));

			}));
		};
	}
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}
