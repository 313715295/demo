package com.zwq.daoservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableDubboConfiguration
public class DemoDaoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoDaoApplication.class, args);
		new SpringApplicationBuilder(DemoDaoApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}
}
