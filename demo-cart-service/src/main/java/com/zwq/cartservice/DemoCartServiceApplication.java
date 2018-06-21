package com.zwq.cartservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.zwq"})
@SpringBootApplication
@EnableDubboConfiguration
public class DemoCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCartServiceApplication.class, args);
	}
}
