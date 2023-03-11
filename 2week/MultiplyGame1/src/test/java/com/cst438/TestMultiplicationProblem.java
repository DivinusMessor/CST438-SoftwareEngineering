package com.cst438;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.cst438.controller.MultiplyController;
import com.cst438.dto.MultiplyProblem;
import com.cst438.dto.MultiplyResult;
import com.cst438.service.MultiplyChecker;


@ContextConfiguration(classes = {MultiplyController.class, MultiplyChecker.class})
@WebMvcTest
public class TestMultiplicationProblem {
	@Autowired
	private MockMvc mvc;
	@Test
	public void testGetProblem() throws Exception {
		// verify that factors are between 11 and 99
		for (int i=0; i<1000; i++) {
			MockHttpServletResponse response = mvc.perform(
					get("/multiplication/new").accept(MediaType.APPLICATION_JSON)).
                              andReturn().getResponse();
			assertEquals(200, response.getStatus());
			MultiplyProblem mp = fromJsonString(
                   response.getContentAsString(), 
                   MultiplyProblem.class);
			assertTrue(mp.factorA>=11 && mp.factorA<=99);
			assertTrue(mp.factorB>=11 && mp.factorB<=99);
		}
	}
	@Test
	public void correctAnswer() throws Exception {
		MockHttpServletResponse response = mvc.perform(
  		   get("/multiplication/new").
             accept(MediaType.APPLICATION_JSON)).
             andReturn().getResponse();
		assertEquals(200, response.getStatus());
		MultiplyProblem mp = fromJsonString(
                   response.getContentAsString(), 
                   MultiplyProblem.class);
		mp.attempt=mp.factorA*mp.factorB;
		mp.alias="test";
		response = mvc.perform(
			post("/result").content(asJsonString(mp)).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).
                andReturn().getResponse();
		assertEquals(200, response.getStatus());
		MultiplyResult mr_actual = fromJsonString(
                   response.getContentAsString(), 
                   MultiplyResult.class);
		assertTrue(mr_actual.correct);	
	}
	
	@Test
	public void incorrectAnswer() throws Exception {
		MockHttpServletResponse response = mvc.perform(
                          get("/multiplication/new")
                          .accept(MediaType.APPLICATION_JSON))
                          .andReturn()
                          .getResponse();
		assertEquals(200, response.getStatus());
		MultiplyProblem mp = fromJsonString(response.getContentAsString(), MultiplyProblem.class);

                // given an incorrect attempt 
		mp.attempt=mp.factorA*mp.factorB+1;

		mp.alias="test";
		response = mvc.perform(
				post("/result").content(asJsonString(mp))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                                 .andReturn()
                                 .getResponse();
		assertEquals(200, response.getStatus());
		MultiplyResult mr_actual = fromJsonString(
                                              response.getContentAsString(), 
                                              MultiplyResult.class);
		
		assertFalse(mr_actual.correct);	
	}

}
