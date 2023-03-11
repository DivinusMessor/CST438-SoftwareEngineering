package com.cst438.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.domain.MultiplyAttempt;
import com.cst438.domain.MultiplyAttemptRepository;
import com.cst438.domain.User;
import com.cst438.domain.UserRepository;
import com.cst438.dto.MultiplyResult;

@Service
public class MultiplyHistoryImpl implements MultiplyHistory {
	
	@Autowired
	private MultiplyAttemptRepository multiplyAttemptRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public MultiplyResult save(MultiplyResult mr) {
		// if user does not exist, create it. 
		User user = userRepository.findByAlias(mr.alias);
		if (user==null) {
			user = new User();
			user.setAlias(mr.alias);
			user = userRepository.save(user);
		}
		
		// create attempt entity and save to database
		MultiplyAttempt ma = new MultiplyAttempt(mr, user); 
		ma = multiplyAttemptRepository.save(ma);
		mr.id = ma.getId();
		return mr;
	}
	
	public MultiplyResult[] getHistory(String alias, int last_n) {
		
		ArrayList<MultiplyResult> mr_list = new ArrayList<>();
		Iterable<MultiplyAttempt> ma_list = multiplyAttemptRepository.findByAliasOrderByIdDesc(alias);
		for (MultiplyAttempt ma : ma_list) {
			if (last_n==0) break;
			mr_list.add( new MultiplyResult(ma)) ;
			last_n--;
		}
		return mr_list.toArray(new MultiplyResult[mr_list.size()]);
		
	}

}
