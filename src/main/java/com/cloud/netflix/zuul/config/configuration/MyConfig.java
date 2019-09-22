package com.cloud.netflix.eureka.config.configuration;

import lombok.Data;

import javax.annotation.PostConstruct;

//@Configuration
//@ConfigurationProperties(prefix = "wxpay")// 与配置文件中开头相同
//PropertySource默认取application.properties
//@PropertySource(value = "wxpay.properties")
@Data
public class MyConfig {
	private String appID;
	private String mchID;
	private String key;
	
	@PostConstruct
	public void init() {
		System.out.println();
	}
}