package com.masai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class FloraFortApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloraFortApplication.class, args);
	}


	
	 private ApiInfo apiInfo() {
	        return new ApiInfo(
	                "Flora fort API",
	                "Nursery Management System",
	                "API TOS",
	                "Terms of service",
	                new Contact("Team Florafort", "www.Florafort.com", "Admin@Florafort.com"),
	                "License of API",
	                "API license URL",
	                Collections.emptyList());
	    }

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	                .paths(PathSelectors.any())
	                .build();
	    }

	    /**
	     * SwaggerUI information
	     */

	    @Bean
	    UiConfiguration uiConfig() {
	        return UiConfigurationBuilder.builder()
	                .deepLinking(true)
	                .displayOperationId(false)
	                .defaultModelsExpandDepth(1)
	                .defaultModelExpandDepth(1)
	                .defaultModelRendering(ModelRendering.EXAMPLE)
	                .displayRequestDuration(false)
	                .docExpansion(DocExpansion.NONE)
	                .filter(false)
	                .maxDisplayedTags(null)
	                .operationsSorter(OperationsSorter.ALPHA)
	                .showExtensions(false)
	                .tagsSorter(TagsSorter.ALPHA)
	                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
	                .validatorUrl(null)
	                .build();
	    }
	
	}
	

