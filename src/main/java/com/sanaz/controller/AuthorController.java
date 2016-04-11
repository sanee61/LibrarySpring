package com.sanaz.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanaz.model.Author;
import com.sanaz.model.Book;
import com.sanaz.service.AuthorDTO;
import com.sanaz.service.BookDTO;



@RestController
public class AuthorController {
	
	@RequestMapping("/hello")
	public String demo(){
	return "test from spring";
	}
	
	@RequestMapping("/authors")
	public ArrayList<Author> getAuthors(){
		AuthorDTO authorDto = new AuthorDTO();
		ArrayList<Author> authors = authorDto.getAuthors();
	return authors;
	}
	
	@RequestMapping("/authorsName")
	public ArrayList<String> getAuthorsNames(){
		AuthorDTO authorDto = new AuthorDTO();
		ArrayList<Author> authors = authorDto.getAuthors();
		ArrayList<String> authorsName =  new ArrayList<String>();
		for (Author author:authors){
			authorsName.add(author.getAuthorName());
		}
	return authorsName;
	}
	
	@RequestMapping("/authors/{id}")
	public Author getAuthorById(@PathVariable("id") int id){
		AuthorDTO authorDto = new AuthorDTO();
		Author author = authorDto.getAuthorById(id);
	return author;
	}
	
	@RequestMapping("/books/{id}")
	public Book getBookById(@PathVariable("id") int id){
		BookDTO bookDto = new BookDTO();
		Book book = bookDto.getBookById(id);
		return book;
	}
}
