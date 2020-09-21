package com.lhm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhm.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo myApiInfo() {
        return new ApiInfoBuilder()
                .title("多弗朗明哥的api文档")
                .description("不吃香的不吃辣的，只吃臭的")
                .version("1.0.0")
                .termsOfServiceUrl("http://127.0.0.1:8087")
                .contact(new Contact("李海明", "https://github.com/jerrylhm/foodie", "403843422@qq.com"))
                .build();
    }
}
