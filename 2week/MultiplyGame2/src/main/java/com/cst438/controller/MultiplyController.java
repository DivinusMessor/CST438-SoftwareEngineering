package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.dto.MultiplyProblem;
import com.cst438.service.RandomProblem;

@RestController
public class MultiplyController {
	
	@Autowired
	private RandomProblem generator;
	
	@GetMapping("/multiplication/new")
	public MultiplyProblem getProblem() {
		MultiplyProblem mp = generator.getProblem();
		return mp;
	}

}
