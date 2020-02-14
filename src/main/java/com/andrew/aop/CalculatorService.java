package com.andrew.aop;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
	
	public int addNums(int a, int b) {
		System.out.println("Currently adding");
		return a + b;
	}
	
	public int multiplyNums(int a, int b) {
		return a * b;
	}
	
	public double divideNums(double a, double b) {
		System.out.println("Currently dividing");
		if (b == 0d) {
			throw new DivideByZeroException();
		} else {
			return a / b;
		}
	}
}
