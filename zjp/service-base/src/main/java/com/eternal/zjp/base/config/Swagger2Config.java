package com.eternal.zjp.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther Eternal
 * @Date 2021/9/22
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminAPiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminAPiInfo() {
        return new ApiInfoBuilder()
                .title("枝江心理后台管理系统API文档")
                .description("本文档描述了枝江心理后台管理系统的各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("Eternal", "http://yeyeyeyeto.ren", "2875381616@qq.com"))
                .build();
    }

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webAPiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    private ApiInfo webAPiInfo() {
        return new ApiInfoBuilder()
                .title("枝江心理网站API文档")
                .description("本文档描述了枝江心理网站的各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("Eternal", "http://yeyeyeyeto.ren", "2875381616@qq.com"))
                .build();
    }

    @Bean
    public Docket apiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(apiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();

    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("枝江心理-API文档")
                .description("本文档描述了枝江心理接口")
                .version("1.0")
                .contact(new Contact("Eternal", "http://yeyeyeyeto.ren", "2875381616@qq.com"))
                .build();
    }

}
