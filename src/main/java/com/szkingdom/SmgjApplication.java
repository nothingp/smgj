package com.szkingdom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.szkingdom")
@ServletComponentScan
@MapperScan("com.szkingdom.mapper.dao")
@EnableCaching
public class SmgjApplication {

	public static void main(String[] args) {
//		SpringApplication runner = new SpringApplication(SmgjApplication.class);
//		runner.setBannerMode(Banner.Mode.OFF);
//		runner.run(args);
		SpringApplication.run(SmgjApplication.class, args);
	}
}
