package com.needle.demoApi.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.needle.demoApi.model.Author;
import com.needle.demoApi.repository.AuthorRepository;

@RunWith(SpringRunner.class)
public class AuthorServiceTest {
	
	@MockBean
	private AuthorService authorService;
	
	@MockBean
	private AuthorRepository authorRepository;
	
	@Test
	public void testsaveAuthor() {
		Author author = new Author("101","Chetan","2-state");
		Author response = authorRepository.save(author);
		
		
		assertThat(response.getName()).isEqualTo(author.getName());
	}
}
