package com.cst438.service;

import com.cst438.dto.MultiplyResult;

public interface MultiplyHistory {
	/*
	 * save the user attempt  
	 */
	public MultiplyResult save(MultiplyResult mr);
	
	/*
	 * get the last N attempts for the user alias
	 */
	public MultiplyResult[] getHistory(String alias, int last_n);	
}
