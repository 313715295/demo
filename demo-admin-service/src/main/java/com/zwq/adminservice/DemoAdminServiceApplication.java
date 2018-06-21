package com.zwq.adminservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.zwq"})
@EnableDubboConfiguration
public class DemoAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAdminServiceApplication.class, args);

	}
}
