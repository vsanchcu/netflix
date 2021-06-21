/*
 * SwaggerConfig
 * 
 * @author: VSANCHCU
 * @version: 1.0
 */
package com.vsanchcu.netflix.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 * 
 * http://localhost:8080/swagger-ui.html
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Netflix Service API", 
				"Netflix Service API Description", 
				"1.0", 
				"", 
				new springfox.documentation.service.Contact("", "", ""), 
				"", 
				"", 
				Collections.emptyList());
	}

}
