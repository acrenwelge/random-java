package com.andrew.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class Config {

	@Bean
	public MyAspect getAspect() {
		System.out.println("Configuration running");
		return new MyAspect();
	}
}
