package com.rs.notedown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@SuppressWarnings({"UnusedDeclaration"})
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Not Authenticated")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rs.notedown"))
                .paths(PathSelectors.ant("/api/auth/**"))
                .build();
    }

    @Bean
    public Docket authTokenSecuredApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Collections.singletonList(new ApiKey(
                        "JWT",
                        "Authorization",
                        "header")))
                .securityContexts(Collections.singletonList(securityContext()))
                .groupName("Authenticated By Token")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rs.notedown"))
                .paths(PathSelectors.regex("^(?!.*auth).*$"))
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                        SecurityReference.builder()
                                .reference("JWT")
                                .scopes(new AuthorizationScope[0])
                                .build()
                )).build();
    }
}
