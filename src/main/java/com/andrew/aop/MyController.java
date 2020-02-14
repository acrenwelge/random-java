package com.andrew.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@Autowired
	private CalculatorService cs;
	
	@GetMapping("/add/{num1}/{num2}")
	public int add(@PathVariable int num1, @PathVariable int num2) {
		return cs.addNums(num1, num2);
	}
}
