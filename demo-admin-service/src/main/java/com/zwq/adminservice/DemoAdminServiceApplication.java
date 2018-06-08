package com.zwq.adminservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableDubboConfiguration
public class DemoAdminServiceApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoAdminServiceApplication.class, args);
		new SpringApplicationBuilder(DemoAdminServiceApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}
}
