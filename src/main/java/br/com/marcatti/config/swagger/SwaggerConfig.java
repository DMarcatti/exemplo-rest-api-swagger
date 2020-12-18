package br.com.marcatti.config.swagger;

import br.com.marcatti.controller.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.awt.print.Pageable;



@Configuration
@ComponentScan(basePackageClasses = {
		Controller.class
})
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.marcatti.controller"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Pageable.class);
	}

	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Projeto exemplo rest api swagger")
				.description("Exemplo de projeto para estudo")
				.version("v0.0.1")
				.build();
	}


}