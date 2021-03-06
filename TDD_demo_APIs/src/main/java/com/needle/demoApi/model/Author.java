package com.needle.demoApi.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "Book")
public class Author {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	 @Column
	private String authorIdentifier;
	 @Column
	private String name;
	 @Column
	private String bookName;
	
	public Author() {
		
	}
	public Author(String authorIdentifier, String name, String bookName) {
		// TODO Auto-generated constructor stub
		this.authorIdentifier=authorIdentifier;
		this.name = name;
		this.bookName = bookName;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public String getAuthorIdentifier() {
		return authorIdentifier;
	}
	public void setAuthorIdentifier(String authorIdentifier) {
		this.authorIdentifier = authorIdentifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	

}
