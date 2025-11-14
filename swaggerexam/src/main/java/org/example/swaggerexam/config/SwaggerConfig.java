package org.example.swaggerexam.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import java.security.Security;

@Configuration
@OpenAPIDefinition(
        info=@Info(title = "멋사 모임 및 일정 과리 API",
        version = "1.0",
                description = "멋쟁이 사자처럼의 모임 및 일정을 관리하기 위한 API 문서"
        )
//        , security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
