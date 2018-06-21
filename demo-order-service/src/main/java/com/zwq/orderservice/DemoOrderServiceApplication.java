package com.zwq.orderservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.zwq"})
@EnableDubboConfiguration
public class DemoOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOrderServiceApplication.class, args);
	}
}
