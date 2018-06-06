package com.zwq.daoservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DemoDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDaoApplication.class, args);
	}
}
