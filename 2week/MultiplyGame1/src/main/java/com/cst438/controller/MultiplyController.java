package com.cst438.controller;


import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.dto.MultiplyProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cst438.dto.*;
import com.cst438.service.*;	



	




@RestController
public class MultiplyController {
	
	private final int LOWER = 11;  // lower and upper limits (inclusive) for factors.
	private final int UPPER = 99;
	
	private Random generator = new Random();
	
    @Autowired
	private MultiplyChecker checker; 
	
	@GetMapping("/multiplication/new")
	public MultiplyProblem getProblem() {
		MultiplyProblem mp = new MultiplyProblem();
		mp.factorA = getRandomFactor();
		mp.factorB = getRandomFactor();
		return mp;
	}
	
	/*
	 * check the user's answer  
	 */
	@PostMapping("/result")
	public MultiplyResult check(@RequestBody MultiplyProblem mp) {
		MultiplyResult mr = checker.check(mp);
		return mr; 
	}
	
	/*
	 * generate a random integer >=LOWER and <=UPPER 
	 */
	private int getRandomFactor() {
		return generator.nextInt(UPPER-LOWER+1)+LOWER;
	}


}
