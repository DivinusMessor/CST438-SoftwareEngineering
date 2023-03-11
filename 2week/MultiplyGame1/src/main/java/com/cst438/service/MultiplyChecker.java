package com.cst438.service;

import org.springframework.stereotype.Service;

import com.cst438.dto.MultiplyProblem;
import com.cst438.dto.MultiplyResult;

@Service
public class MultiplyChecker {
	
	public MultiplyResult check(MultiplyProblem mp) {
		
		MultiplyResult mr = new MultiplyResult();
		mr.alias = mp.alias;
		mr.factorA = mp.factorA;
		mr.factorB = mp.factorB;
		mr.attempt = mp.attempt;
		mr.answer = mp.factorA*mp.factorB;
		mr.correct = (mr.attempt == mr.answer);
		return mr;
	}

}
