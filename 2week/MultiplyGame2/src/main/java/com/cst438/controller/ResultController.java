package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.dto.MultiplyProblem;
import com.cst438.dto.MultiplyResult;
import com.cst438.service.MultiplyChecker;
import com.cst438.service.MultiplyHistory;


@RestController
public class ResultController {
	
	@Autowired
	private MultiplyHistory history;
	
	@Autowired
	private MultiplyChecker checker; 
	
	/*
	 * check the user's answer and save the attempt.
	 */
	@PostMapping("/result")
	public MultiplyResult check(@RequestBody MultiplyProblem mp) {
		MultiplyResult mr = checker.check(mp);
		MultiplyResult mr2 = history.save(mr);
		return (mr2==null) ? mr : mr2;
	}
	
	/*
	 * retrieve the last N attempts for a user
	 * Example:
	 * to retrieve the last 5 attempts for user "david"  URI = "/result/david?last=5" 
	 */
	@GetMapping("/result/{alias}") 
	public MultiplyResult[] lastAttempts(@RequestParam("last") int last, @PathVariable("alias") String alias) {
		System.out.printf("last=%d alias=%s \n", last, alias);
		MultiplyResult[] mr = history.getHistory(alias, last);
		return mr;
	}
	
}
