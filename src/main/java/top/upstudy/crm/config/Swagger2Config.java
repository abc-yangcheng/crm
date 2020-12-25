package top.upstudy.crm.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
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
 * 更改swagger2的配置界面
 */
// 访问地址localhost:8080/swagger-ui.html
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Value("${swagger.enable}")
	private Boolean enable;

	//配置Swagger的Docket的bean实例
	@Bean
	public Docket webApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("CRM-Api")
				.apiInfo(webApiInfo())
				.enable(enable) // 是否显示  //后面是一起定义的
				.select()
				.apis(RequestHandlerSelectors.basePackage("top.upstudy.crm.controller"))
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build();
	}

	//配置swagger信息 ApiInfo
	private ApiInfo webApiInfo(){
		return new ApiInfoBuilder()
				.title("CRM客户管理系统-Api")
				.description("CRM-Api")
				.version("1.0")
				.contact(new Contact("WSM", "http://www.upstudy.top", "2331471230@qq.com"))
				.build();
	}

}