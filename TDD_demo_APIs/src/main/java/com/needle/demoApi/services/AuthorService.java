package com.needle.demoApi.services;

import com.needle.demoApi.exception.AuthorIdException;
import com.needle.demoApi.model.Author;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.needle.demo.exceptions.AuthorIdException;
import com.needle.demoApi.model.Author;
import com.needle.demoApi.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author saveAuthor(Author author) {
		// TODO Auto-generated method stub
		try {
			
			return authorRepository.save(author);
		}
		catch(Exception ex) {
			
			throw new AuthorIdException("Author identifier: "+author.getAuthorIdentifier()+" already exist");
		}
	
	}
	
	public Author findAuthorByIdentifier(String authorIdentifier) {
		// TODO Auto-generated method stub
		Author author =   authorRepository.findByAuthorIdentifier(authorIdentifier);
		
		if(author==null) {
			throw new AuthorIdException("Project id: "+authorIdentifier+" does not exist");
		}
		return author;
	}
	
	public List<Author> getAllAuthorInfo() {
		Iterable<Author> authorDataList = authorRepository.findAll();
		return ((Collection<Author>) authorDataList).stream().collect(Collectors.toList());
	}

	public String deleteAuthorByIdentifier(String authorIdentifier) {
		// TODO Auto-generated method stub
		Author author = authorRepository.findByAuthorIdentifier(authorIdentifier);
		if(author==null) {
			throw new AuthorIdException("Book and author with Id: "+authorIdentifier+" does not exist");
		}
		authorRepository.delete(author);
		return "deleted "+authorIdentifier+" successfully";
	}

	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		try {
			Author author1 =   authorRepository.findByAuthorIdentifier(author.getAuthorIdentifier());
			if(author1!=null) {
				author1.setBookName(author.getBookName());
				author1.setName(author.getName());
				return authorRepository.save(author1);
			}
			else {
				throw new AuthorIdException("Book and author with Id: "+author.getAuthorIdentifier()+" does not exist");
			}
		 }
		 catch(Exception ex) {
				throw new AuthorIdException("Book identifier: "+author.getAuthorIdentifier()+" already exist");
			}
			
	}
	
	
}