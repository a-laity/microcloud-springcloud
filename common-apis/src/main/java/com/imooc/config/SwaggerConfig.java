package com.imooc.config;

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

/**
 * @author xujunchen
 * @date 2023/5/16 0:26
 * @describe todo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("小徐工作室提供者微服务")
                .description("实现部门数据的增删改查")
                .termsOfServiceUrl("www.imooc.com")
                .contact(new Contact("爆浆小可爱","edu.imooc.com","921014462@qq.com"))
                .license("小徐工作室").version("1.0.0").build();
    }
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2) // 使用的文档版本类型
                .apiInfo(this.getApiInfo())
                .select() // 所有的接口一定要放在指定的包中
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any()).build();
    }
}
