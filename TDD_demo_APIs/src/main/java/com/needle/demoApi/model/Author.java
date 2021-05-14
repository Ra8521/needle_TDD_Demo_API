package com.needle.demoApi.model;

public class Author {
	
	
	private Long Id;
	private String bookIdentifier;
	private String name;
	private String bookName;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getBookIdentifier() {
		return bookIdentifier;
	}
	public void setBookIdentifier(String bookIdentifier) {
		this.bookIdentifier = bookIdentifier;
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
