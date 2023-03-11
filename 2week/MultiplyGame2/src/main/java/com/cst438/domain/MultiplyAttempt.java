package com.cst438.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cst438.dto.MultiplyResult;

 
@Entity
public class MultiplyAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int factorA;
	private int factorB;
	private int attempt;
	private int answer;
	private boolean correct;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public MultiplyAttempt() {
		super();
	}
	
	public MultiplyAttempt(MultiplyResult mr, User user) {
		super();
		this.id = mr.id;
		this.user = user;
		this.factorA = mr.factorA;
		this.factorB = mr.factorB;
		this.attempt = mr.attempt;
		this.answer = mr.answer;
		this.correct = mr.correct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getFactorA() {
		return factorA;
	}

	public void setFactorA(int factorA) {
		this.factorA = factorA;
	}

	public int getFactorB() {
		return factorB;
	}

	public void setFactorB(int factorB) {
		this.factorB = factorB;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
