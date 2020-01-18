package com.gyumin.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// JUnit에 내장된 Runner(실행자)외에 추가적인 runner를 실행
// Spring Boot Test와 JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
// Web(Spring MVC)에 집중하고자 할 때
// @Controller, @ControllerAdvice 등에 대한 테스트가 가능
@WebMvcTest
public class HelloControllerTest {

	// 웹 API를 테스트할 때 사용
	// 스프링 MVC 테스트의 시작점
	@Autowired
	private MockMvc mvc;

	@Test
	public void hello가_리턴된다() throws Exception {
		String hello = "hello";

		mvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(hello));
	}

	@Test
	public void helloDto가_리턴된다() throws Exception {
		String name = "hello";
		int amount = 1000;

		mvc.perform(
			get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(name)))
			.andExpect(jsonPath("$.amount", is(amount)));
	}
}
