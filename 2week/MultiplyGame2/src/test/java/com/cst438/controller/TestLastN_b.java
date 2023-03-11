package com.cst438.controller;

import static com.cst438.test.utils.TestUtils.asJsonString;
import static com.cst438.test.utils.TestUtils.fromJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.cst438.domain.MultiplyAttempt;
import com.cst438.domain.MultiplyAttemptRepository;
import com.cst438.domain.User;
import com.cst438.domain.UserRepository;
import com.cst438.dto.MultiplyProblem;
import com.cst438.dto.MultiplyResult;
import com.cst438.service.MultiplyChecker;
import com.cst438.service.MultiplyHistoryImpl;
import com.cst438.service.RandomProblem;

@ContextConfiguration(classes = { MultiplyController.class, ResultController.class, MultiplyChecker.class,
		MultiplyHistoryImpl.class, RandomProblem.class })
@WebMvcTest
public class TestLastN_b {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private MultiplyAttemptRepository multiplyAttemptRepository;

	private ArrayList<MultiplyAttempt> save_list = new ArrayList<>();


	@Test
	public void test() throws Exception {

		int N_ATTEMPTS = 2;

		User test_user = new User();
		test_user.setAlias("test");
		test_user.setId(1);

		// define stub that returns User entity given alias value "test"
		given(userRepository.findByAlias("test")).willReturn(test_user);

		// define stub that will save MultiplyAttempt entity to ArrayList
		// set the Id value, and return the entity.
		given(multiplyAttemptRepository.save(any(MultiplyAttempt.class)))
				.willAnswer(new Answer<MultiplyAttempt>() {
					@Override
					public MultiplyAttempt answer(InvocationOnMock invocation) throws Throwable {
						MultiplyAttempt ma = invocation.getArgument(0);
						save_list.add(0, ma);
						ma.setId(save_list.size());
						return ma;
					}
				});


		// define stub that returns the ArrayList
		// the most recent entry will be first, the oldest will be last
		given(multiplyAttemptRepository.findByAliasOrderByIdDesc("test"))
				.willAnswer(new Answer<Iterable<MultiplyAttempt>>() {
					@Override
					public Iterable<MultiplyAttempt> answer(InvocationOnMock invocation) throws Throwable {
						return save_list;
					}
				});

		// perform several attempts at solving a random multiplication problem
		for (int i = 0; i < N_ATTEMPTS; i++) {
			MockHttpServletResponse response = mvc
					.perform(get("/multiplication/new").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
			assertEquals(200, response.getStatus());
			MultiplyProblem mp = fromJsonString(response.getContentAsString(), MultiplyProblem.class);
			mp.attempt = mp.factorA * mp.factorB;
			mp.alias = "test";

			response = mvc.perform(post("/result").content(asJsonString(mp)).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
			assertEquals(200, response.getStatus());
		}

		// retrieve the history of attempts.
		MockHttpServletResponse response = mvc
				.perform(get("/result/test?last=" + N_ATTEMPTS).accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();
		assertEquals(200, response.getStatus());
		MultiplyResult[] mr = fromJsonString(response.getContentAsString(), MultiplyResult[].class);
		assertEquals(N_ATTEMPTS, mr.length);
		for (int i = 0; i < mr.length; i++) {
			assertEquals(N_ATTEMPTS - i, mr[i].id);
			assertTrue(mr[i].correct);
		}

	}

}
