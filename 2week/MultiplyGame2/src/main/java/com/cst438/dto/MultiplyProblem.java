package com.cst438.dto;

public class MultiplyProblem {
	
	public String alias;
	public int factorA;
	public int factorB;
	public int attempt;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiplyProblem other = (MultiplyProblem) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (attempt != other.attempt)
			return false;
		if (factorA != other.factorA)
			return false;
		if (factorB != other.factorB)
			return false;
		return true;
	}
}
