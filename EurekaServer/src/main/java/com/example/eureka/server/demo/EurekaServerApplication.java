package com.example.eureka.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
//@Configuration
//@EnableAutoConfiguration //自动加载配置信息
@ComponentScan("com.example.eureka.server.demo")//使包路径下带有注解的类可以使用@Autowired自动注入
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
