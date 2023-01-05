package com.example.demo.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer{
	@Bean
	 public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                /**
                 * them securityContexts, securitySchemes de add thang bearer token tren swagger-ui
                 */
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();	 }
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
	
	private ApiInfo apiInfo(){
        return new ApiInfo(
                "Phuc Do Learn Srping Rest + Jwt Bearer",
                "Description",
                "2.0",
                "Term of function services",
                new Contact("Phuc Do Learn", "www.luigicode77.es", "micorreo@gmail.com"),
                "Phuc Do",
                "",
                Collections.emptyList()
        );
    }
}
