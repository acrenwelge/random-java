package com.andrew.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MyAspect {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.andrew.aop.CalculatorService.addNums(..))")
	public void beforeAdding(JoinPoint jp) {
		System.out.println("TESTING");
		log.info("About to add!");
		log.info("BEFORE - Join point: {}", jp.toLongString());
		Object[] args = jp.getArgs();
		log.info((Integer) args[0] + "  " + (Integer) args[1]);
	}
	
	@After("execution(* com.andrew.aop.CalculatorService.addNums(..))")
	public void afterAdding(JoinPoint jp) {
		log.info("AFTER - Finished adding!");
	}
	
	// executes around all methods in CalculatorService
	@Around("execution(* com.andrew.aop.CalculatorService.*(..))")
	public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("AROUND - before join point execution");
		Object res = pjp.proceed();
		long end = System.currentTimeMillis();
		log.info("AROUND - after join point execution");
		log.info("Time taken was {}ms", (end - start));
		return res;
	}
	
	@AfterReturning(value="execution(* com.andrew.aop.CalculatorService.addNums(..))",
			returning="result")
	public void successfulAddition(JoinPoint jp, Object result) {
		log.info("AFTER RETURNING - successful addition! Result: {}", result);
	}
	
	@AfterReturning(value="execution(* com.andrew.aop.CalculatorService.divideNums(..))",
			returning="result")
	public void successfulDivide(JoinPoint jp, Object result) {
		log.info("AFTER RETURNING - successful division! Result: {}", result);
	}
	
	@AfterThrowing(value="execution(* com.andrew.aop.CalculatorService.divideNums(..))",
			throwing="error")
	public void unsuccessfulDivide(JoinPoint jp, Throwable error) {
		log.info("AFTER THROWING - division problem :(  - {}", error);
	}
}
