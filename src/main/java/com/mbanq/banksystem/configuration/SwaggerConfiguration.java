package com.mbanq.banksystem.configuration;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.any())//
                .paths(Predicates.not(PathSelectors.regex("/error")))//
                .build()//
                .apiInfo(metadata())//
                .useDefaultResponseMessages(false)//
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))//
                .tags(new Tag("Auth", "Auth API guide"))//
                .tags(new Tag("Consumer", "Consumer API guide"))//
                .tags(new Tag("Account", "Account API guide"))//
                .tags(new Tag("Card", "Card API guide"))//
                .tags(new Tag("Transaction", "Transaction API guide"))//
                .genericModelSubstitutes(Optional.class);


    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()//
                .title("Card Management API Documentation")//
                .description(
                        "This is documentation of REST micro-service for managing debit / credit cards with JWT authentication service.  you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".")//
                .version("1.0.0")//
                .license("GitHub").licenseUrl("https://github.com/wecambodev/cardmanagement")//
                .contact(new Contact(null, null, "phuongphally@gmail.com"))//
                .build();
    }


}
