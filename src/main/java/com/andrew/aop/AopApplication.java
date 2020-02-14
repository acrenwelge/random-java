package com.andrew.aop;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {
	
	@Autowired
	private CalculatorService cs;
	
	private static Logger log = LoggerFactory.getLogger(AopApplication.class);

	public static void main(String[] args) {
		log.info("STARTING UP");
		SpringApplication.run(AopApplication.class, args);
		log.info("COMPLETED");
	}
	
	@Bean
	  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {

	      System.out.println("Let's inspect the beans provided by Spring Boot:");

	      String[] beanNames = ctx.getBeanDefinitionNames();
	      Arrays.sort(beanNames);
	      for (String beanName : beanNames) {
	        System.out.println(beanName);
	      }
		  int sum = cs.addNums(3, 5);
		  int product = cs.multiplyNums(sum, 10);
		  try {
			  double badResult = cs.divideNums(product, 0);
		  } catch(DivideByZeroException dbz) {
			  System.err.println("Ignoring...");
		  }
		  double goodResult = cs.divideNums(product, 10);
		  log.info("RESULTS:");
		  log.info("sum: {}, product: {}, dividend: {}", sum, product, goodResult);
	    };
	  }
}
