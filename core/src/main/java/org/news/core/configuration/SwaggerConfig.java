package org.news.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createDocket() {
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.news.core.controllers"))
                .paths(PathSelectors.any())
                .build();
        docket.apiInfo(metaData());
        return docket;
    }

    private ApiInfo metaData() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("NEWS REST API");
        builder.description("Spring Boot REST API for news store");
        builder.version("1.0.0");
        builder.license("Apache License Version 2.0");
        builder.contact(new Contact("Mateusz Gańko", null, "mateuszganko@gmail.com"));
        return builder.build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        UiConfiguration uiConfiguration = UiConfigurationBuilder.builder().build();
        return uiConfiguration;
    }
}
