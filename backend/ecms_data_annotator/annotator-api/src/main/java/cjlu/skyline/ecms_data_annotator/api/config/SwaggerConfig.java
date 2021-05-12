package cjlu.skyline.ecms_data_annotator.api.config;

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
 * @author jinpenglin
 * @version 1.0
 * @description
 * @date 2021/1/14
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .pathMapping("/")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("cjlu.skyline.ecms_data_annotator.api.controller"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(new ApiInfoBuilder()
//                        .title("SpringBoot整合Swagger")
//                        .description("SpringBoot with Swagger")
//                        .version("9.0")
//                        .contact(new Contact("xxx","xxx","nsp4289@autuni.ac.nz"))
//                        .license("The Apache License")
//                        .licenseUrl("http://www.baidu.com")
//                        .build());
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cjlu.skyline.ecms_data_annotator.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Swagger to build RESTFUl APIS in SpringBOOT")
                .description("More http://www.baidu.com")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact("sunf")
                .version("1.0")
                .build();
    }
}
