package com.zwq.demoshow;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DemoShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoShowApplication.class, args);
	}
}
