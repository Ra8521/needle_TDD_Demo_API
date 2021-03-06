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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



import com.needle.demoApi.model.Author;
import com.needle.demoApi.repository.AuthorRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {
	

	@Mock
	private AuthorRepository authorRepository;
	
	@Mock
	private Author author;
	
	@InjectMocks
	private AuthorService authorService;
	
	
	@Test
	public void testsaveAuthor() {
		// author = new Author("101","Chetan","2-state");
		author.setId((long)1);
		author.setAuthorIdentifier("101");
		author.setBookName("2-state");
		author.setName("chatan");
		when(authorRepository.save(any(Author.class))).thenReturn(new Author());
		
		Author response = authorService.saveAuthor(author);
		assertThat(response.getName()).isSameAs(author.getName());
	}
	
	
	@Test
	public void testfindAuthorByIdentifier() {
		// author = new Author("101","Chetan","2-state");
		String AuthorIdentifier="101";
		author.setId((long)1);
		author.setAuthorIdentifier(AuthorIdentifier);
		author.setBookName("2-state");
		author.setName("chatan");
		when(authorRepository.findByAuthorIdentifier(anyString())).thenReturn(new Author());
		
		Author response = authorService.findAuthorByIdentifier(AuthorIdentifier);
		assertThat(response.getName()).isSameAs(author.getName());
	}
	
	@Test
	public void testdeleteAuthorByIdentifier() {
		// author = new Author("101","Chetan","2-state");
		String AuthorIdentifier="101";
		author.setId((long)1);
		author.setAuthorIdentifier(AuthorIdentifier);
		author.setBookName("2-state");
		author.setName("chatan");
		/*when(authorRepository.delete(any(Author.class))).thenReturn();
		*/
		//when(authorRepository.delete((Author)any(Author.class))).thenReturn();
		
		String response = authorService.deleteAuthorByIdentifier(AuthorIdentifier);
		assertThat(response).isSameAs("deleted "+AuthorIdentifier+" successfully");
	}
}
