package com.rt.base.framework.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * API接口文档启动配置
 * @author: wq
 * @review:
 * @date: 2019/12/21 10:46
 * @version: 1.0
 */
@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUI
@Profile({"dev","test"})
public class SwaggerConfig {

	/**
	 * controller接口所在的包
	 */
	@Value("${swagger.basePackage}")
	private String basePackage;

	/**
	 * 当前文档的标题
	 */
	@Value("${swagger.title}")
	private String title;

	/**
	 * 当前文档的详细描述
	 */
	@Value("${swagger.description}")
	private String description;

	/**
	 * 当前文档的版本
	 */
	@Value("${swagger.version}")
	private String version;

	// 定义分隔符,配置Swagger多包
	private static final String splitor = ";";

	@Bean
	public Docket api(ApiInfo apiInfo) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(basePackage(basePackage))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(setHeaderToken());
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.version(version)
				.build();
	}

	private List<Parameter> setHeaderToken() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("StringText")).parameterType("header").required(false).build();
		ParameterBuilder languagePar = new ParameterBuilder();
		tokenPar.name("LANGUAGE_CODE").description("language(zh/en/jp......)").modelRef(new ModelRef("StringText")).parameterType("header").required(false).build();
		List<Parameter> pars = new ArrayList<>();
		pars.add(tokenPar.build());
		return pars;
	}

	/**
	 * 重写basePackage方法，使能够实现多包访问，复制贴上去
	 * @author  teavamc
	 * @date 2019/1/26
	 * @param basePackage
	 * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
	 */
	public static Predicate<RequestHandler> basePackage(final String basePackage) {
		return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
	}

	private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
		return input -> {
			// 循环判断匹配
			for (String strPackage : basePackage.split(splitor)) {
				boolean isMatch = input.getPackage().getName().startsWith(strPackage);
				if (isMatch) {
					return true;
				}
			}
			return false;
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		return Optional.fromNullable(input.declaringClass());
	}
}
