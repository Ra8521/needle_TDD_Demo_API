package com.needle.demoApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.needle.demoApi.model.Author;
public interface AuthorRepository extends CrudRepository<Author,Long>{


	Author findByAuthorIdentifier(String bookIdentifier);
	public Author findByName(String string) ;
	//public Author save(Author author);

}