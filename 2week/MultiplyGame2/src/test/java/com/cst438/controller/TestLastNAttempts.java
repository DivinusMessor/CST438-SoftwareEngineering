package com.cst438.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static com.cst438.test.utils.TestUtils.fromJsonString;
import static com.cst438.test.utils.TestUtils.asJsonString;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;



import com.cst438.dto.*;
import com.cst438.service.*;


@ContextConfiguration(classes = { MultiplyController.class, ResultController.class, MultiplyChecker.class, MultiplyHistoryTestImpl.class, RandomProblem.class })
@WebMvcTest
public class TestLastNAttempts {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void lastNattempts() throws Exception  {
		
		
		int N_ATTEMPTS = 5;
		int LAST = 3;
		
		for (int i=0; i< N_ATTEMPTS; i++) {
			MockHttpServletResponse response = mvc.perform(
					get("/multiplication/new").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
			assertEquals(200, response.getStatus());
			MultiplyProblem mp = fromJsonString(response.getContentAsString(), MultiplyProblem.class);
			mp.attempt=mp.factorA*mp.factorB;
			mp.alias="test";
			
			response = mvc.perform(
					post("/result").content(asJsonString(mp)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
			assertEquals(200, response.getStatus());
		}
		
		
		MockHttpServletResponse response = mvc.perform(
				get("/result/test?last="+LAST).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertEquals(200, response.getStatus());
		MultiplyResult[] mr = fromJsonString(response.getContentAsString(), MultiplyResult[].class);
		assertEquals(LAST, mr.length);
		for (int i=0; i<mr.length; i++) {
			assertEquals(N_ATTEMPTS-i, mr[i].id);
			assertTrue(mr[i].correct);
		}

	}
}
