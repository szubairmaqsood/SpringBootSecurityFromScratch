package com.example.SecurityVersion1.SwaggerConfiguration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfigurations {
    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.SecurityVersion1.Student.controller"))
                .paths(regex("/api/v1/students.*"))
                .build()

    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Spring Boot2.X Kotlin Use in Swagger2 structure RESTFul APIs")
                .description("In this application a basic Swagger 2 Api is made")
                .termsOfServiceUrl("Do not copy it")
                .contact(Contact("Muhammad Zubair", "followz.com", "szubair1833@gmail.com"))
                .version("1.0.0")
                .build()
    }
}