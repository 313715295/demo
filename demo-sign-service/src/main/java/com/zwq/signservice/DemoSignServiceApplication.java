package com.zwq.signservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.zwq"})
@SpringBootApplication
@EnableDubboConfiguration
public class DemoSignServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSignServiceApplication.class, args);

	}
}
