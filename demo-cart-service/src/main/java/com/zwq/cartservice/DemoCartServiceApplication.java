package com.zwq.cartservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DemoCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCartServiceApplication.class, args);
	}
}
