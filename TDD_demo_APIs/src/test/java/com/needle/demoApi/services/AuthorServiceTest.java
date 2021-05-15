package com.needle.demoApi.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;



import com.needle.demoApi.model.Author;
import com.needle.demoApi.repository.AuthorRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {
	
	@Mock
	private AuthorService authorService;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@Test
	public void testsaveAuthor() {
		Author author = new Author("101","Chetan","2-state");
		
		when(authorRepository.save((Author) any(Author.class))).thenReturn(new Author());
		//Matchers.<Class<A>>any())
		
		Author response = authorRepository.save(author);
		assertThat(response.getName()).isEqualTo(author.getName());
	}
}
