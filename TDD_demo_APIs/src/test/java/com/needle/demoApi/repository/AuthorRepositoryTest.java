package com.needle.demoApi.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.needle.demoApi.model.Author;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryTest {
	@Autowired
	private AuthorRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void get_Authordetails() throws Exception {
		 entityManager.persist(new Author("111", "chetan", "2-state"));
		Author author = repository.findByName("chetan");
		assertThat(author.getName()).isEqualTo("chetan");
	}

}