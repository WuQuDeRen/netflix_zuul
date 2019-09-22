package com.cloud.netflix.zuul.config.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private MyInterceptor1 myInterceptor1;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(myInterceptor1).addPathPatterns(new String[]{"/test/**"});
	}
}
