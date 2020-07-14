package com.md.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class have all the configuration required for Swagger 2 running with Spring fox.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Autowired
	private BuildProperties buildProperties;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		String appName = buildProperties.getArtifact();
		String appVersion = buildProperties.getVersion();

		return new ApiInfoBuilder()
				.title(appName)
				.description("Simple app documentation page, This is for test purpose")
				.version(appVersion)
				.contact(new Contact("Ion Isac", "http://www.youtube.com", null))
				.build();
	}
}
