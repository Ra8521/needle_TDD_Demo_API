package com.needle.demoApi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.needle.demoApi.model.Author;
import com.needle.demoApi.services.AuthorService;
import com.needle.demoApi.services.MapValidationErrorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	public AuthorService authorService;
	
	@Autowired
	  private MapValidationErrorService MapValidationService;
	
	 @PostMapping( consumes = "application/json", produces = "application/json")
	    public ResponseEntity<?> createNewAuthor(@Validated @RequestBody Author author, BindingResult result){
		 	
	    	ResponseEntity<?> errormap = MapValidationService.MapValidationService(result);
	    	if(errormap!=null) {
	    		return errormap;
	    	}
	        Author author1 = authorService.saveAuthor(author);
	        return new ResponseEntity<Author>(author1, HttpStatus.CREATED);
	    }
	 
	 @GetMapping(value = "/allauthor", produces = "application/json")
		public List<Author> getAllAuthorinfo() {
			return authorService.getAllAuthorInfo();
		}
		
		@GetMapping(value = "/{authorIdentifier}",  produces = "application/json")
	    public ResponseEntity<?> getAuthorById(@PathVariable("authorIdentifier") String authorIdentifier){
	    	return new ResponseEntity<Author>(authorService.findAuthorByIdentifier(authorIdentifier), HttpStatus.OK);
	    }
		
		 @PutMapping(  consumes = "application/json",produces = "application/json")
			public ResponseEntity<?> updateAuthor(@RequestBody Author author){
				
				Author author1 = authorService.updateAuthor(author);
		    	return new ResponseEntity<Author>(author1, HttpStatus.OK);
		    }
	
		
		@DeleteMapping("/{authorIdentifier}")
	    public ResponseEntity<?> deleteAuthorById(@Validated @PathVariable String authorIdentifier){
			authorService.deleteAuthorByIdentifier(authorIdentifier);
	    	return new ResponseEntity<String>("Book & author detail with id: "+authorIdentifier + " is deleted", HttpStatus.OK);
	    }
}
