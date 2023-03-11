package com.cst438.service;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.cst438.dto.MultiplyProblem;

@ExtendWith(MockitoExtension.class)
public class TestRandomProblem {
	
	private RandomProblem generator;
	
	@Mock
	private Random random;
	
	@BeforeEach
	public void setup() {
		generator = new RandomProblem(random);
	}
	
	@Test
	public void testRandomProblem() {
		// given that the next two random numbers are 20, 30
		// then the factors should be 31, 41
		given(random.nextInt(89)).willReturn(80, 52);
		MultiplyProblem problem = generator.getProblem();
		assertEquals(91, problem.factorA);
		assertEquals(63, problem.factorB);
	}


}

