package com.mycom.springbootjparoleshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	// DI
		@Bean
		OpenAPI openAPI() {
			return new OpenAPI()
					.components(new Components())
					.info(apiInfo());
		}

		private Info apiInfo() { // 페이지 위쪽 Title 변경
			return new Info()
					.title("2조 쇼핑몰 API")
					.description("REST API로 구현된 쇼핑몰 기능을 테스트합니다.")
					.version("v0.9");
		}

}
