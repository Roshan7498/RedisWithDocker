package com.roshan.sproject.demoForRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// @EntityScan(basePackages = "com.roshan.sproject.demoForRedis")
@SpringBootApplication(scanBasePackages = "com.roshan.sproject.demoForRedis")
@EnableCaching
public class DemoForRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoForRedisApplication.class, args);
	}

}
