package com.example.devOpsIntegrations.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket postsApi(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/v1.*"), regex("/devops.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Devops Integrations API")
				.description("Devops API reference for developers")
				.contact("ramakishore.gudiseva@gmail.com").license("User License")
				.licenseUrl("ramakishore.gudiseva@gmail.com").version("1.0").build();
	}
}
