package com.needle.demoApi.controller;

import static org.junit.Assert.assertEquals;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.needle.demoApi.model.Author;
import com.needle.demoApi.services.AuthorService;
import com.needle.demoApi.services.MapValidationErrorService;


@RunWith(SpringRunner.class)
@WebMvcTest(value=AuthorController.class)
//@AutoConfigureMockMvc(secure=false)

public class AuthorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorService authorService;
	
	@MockBean
	private MapValidationErrorService MapValidationService;
	
	@Test
	public void testcreateNewAuthor() throws Exception {
		
		Author mockAuthor = new Author();
		mockAuthor.setId((long)101);
		mockAuthor.setBookIdentifier("102");
		mockAuthor.setBookName("2-state");
		mockAuthor.setName("Chetan Bhaghat");
		String inputInJson = this.mapToJson(mockAuthor);
		
		String URI = "/author";
		
		Mockito.when(authorService.saveAuthor(Mockito.any(Author.class))).thenReturn(mockAuthor);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept("application/json").content(inputInJson)
				.contentType("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(201, response.getStatus());
	}
	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
