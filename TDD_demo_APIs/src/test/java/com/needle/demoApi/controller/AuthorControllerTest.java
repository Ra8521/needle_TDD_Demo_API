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
		mockAuthor.setAuthorIdentifier("102");
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
	
	
	@Test
	public void testgetAuthorById() throws Exception {
		Author mockAuthor = new Author();
		mockAuthor.setId((long)101);
		mockAuthor.setAuthorIdentifier("102");
		mockAuthor.setBookName("2-state");
		mockAuthor.setName("Chetan Bhaghat");
		String inputInJson = this.mapToJson(mockAuthor);
		//Mockito.when(bookService.saveAuthor(Mockito.any(Author.class))).thenReturn(mockAuthor);
		String URI = "/author/102";
		Mockito.when(authorService.findAuthorByIdentifier(Mockito.anyString())).thenReturn(mockAuthor);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockAuthor);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testgetAuthorinfo() throws Exception {

		Author mockAuthor = new Author();
		mockAuthor.setId((long)101);
		mockAuthor.setAuthorIdentifier("102");
		mockAuthor.setBookName("2-state");
		mockAuthor.setName("Chetan Bhaghat");
		
		Author mockAuthor2 = new Author();
		mockAuthor.setId((long)104);
		mockAuthor.setAuthorIdentifier("1023");
		mockAuthor.setBookName("Halmet");
		mockAuthor.setName("william ");
		
		List<Author> authorList = new ArrayList<>();
		authorList.add(mockAuthor);
		authorList.add(mockAuthor2);
		
		Mockito.when(authorService.getAllAuthorInfo()).thenReturn(authorList);
		
		String URI = "/author/allauthor";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
						"application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(authorList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testupdateAuthor() throws Exception {
	   
		Author mockAuthor = new Author();
		mockAuthor.setId((long)101);
		mockAuthor.setAuthorIdentifier("102");
		mockAuthor.setBookName("2-state");
		mockAuthor.setName("Chetan Bhaghat");
	   String uri = "/author";
	   String inputJson = this.mapToJson(mockAuthor);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType("application/json").content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Product is updated successsfully");
	}
	
	@Test
	public void testdeleteAuthorById() throws Exception {
		String authorIdentifier="102";
		Author mockAuthor = new Author();
		mockAuthor.setId((long)101);
		mockAuthor.setAuthorIdentifier(authorIdentifier);
		mockAuthor.setBookName("2-state");
		mockAuthor.setName("Chetan Bhaghat");
		String inputInJson = this.mapToJson(mockAuthor);
		//Mockito.when(bookService.saveAuthor(Mockito.any(Author.class))).thenReturn(mockAuthor);
		String URI = "/author/102";
		String res=null;;
		Mockito.when(authorService.deleteAuthorByIdentifier(Mockito.anyString())).thenReturn(res);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				URI);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
		String content = result.getResponse().getContentAsString();
		assertEquals(content, "Book & author detail with id: "+authorIdentifier + " is deleted");
	}
	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
