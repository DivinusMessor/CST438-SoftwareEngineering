package com.cst438.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cst438.dto.MultiplyResult;

/*
 * Unit test double for MultiplyHistory service.
 * Keeps history in memory as ordered ArrayList. 
 */
@Service
public class MultiplyHistoryTestImpl implements MultiplyHistory {
	
	private ArrayList<MultiplyResult> history = new ArrayList<>();

	@Override
	public MultiplyResult save(MultiplyResult mr) {
		history.add(0, mr);
		mr.id = history.size();
		return mr;
	}

	@Override
	public MultiplyResult[] getHistory(String alias, int last_n) {
		ArrayList<MultiplyResult> mr_list = new ArrayList<>();
		for (MultiplyResult mr : history) {
			if (last_n<=0) break;
			if (mr.alias.equals(alias)) {
				mr_list.add(mr) ;
				last_n--;
			}
		}
		return mr_list.toArray(new MultiplyResult[mr_list.size()]);
	}

}
