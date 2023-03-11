package com.cst438.dto;

import com.cst438.domain.MultiplyAttempt;

public class MultiplyResult {
	
	public int id;
	public String alias;
	public int factorA;
	public int factorB;
	public int attempt;
	public int answer;
	public boolean correct;
	
	public MultiplyResult() {
		super();
	}
	
	public MultiplyResult(MultiplyProblem mp) {
		super();
		this.alias = mp.alias;
		this.factorA = mp.factorA;
		this.factorB = mp.factorB;
		this.attempt = mp.attempt;
	}
	
	
	public MultiplyResult(MultiplyAttempt ma) {
		super();
		this.id = ma.getId();
		this.alias = ma.getUser().getAlias();
		this.factorA = ma.getFactorA();
		this.factorB = ma.getFactorB();
		this.attempt = ma.getAttempt();
		this.answer = ma.getAnswer();
		this.correct = ma.isCorrect();
	}

	/*
	 * equals is used in unit test
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiplyResult other = (MultiplyResult) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (answer != other.answer)
			return false;
		if (attempt != other.attempt)
			return false;
		if (id != other.id)
			return false;
		if (correct != other.correct)
			return false;
		if (factorA != other.factorA)
			return false;
		if (factorB != other.factorB)
			return false;
		return true;
	}
	
	
	
}
