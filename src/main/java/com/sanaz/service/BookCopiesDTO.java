package com.sanaz.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanaz.model.Book;
import com.sanaz.model.BookCopies;
import com.sanaz.model.LibraryBranch;
import com.sanaz.util.DBConnection;

public class BookCopiesDTO {
	
	ArrayList<BookCopies> bookCopie = new ArrayList<BookCopies>();
	
	public ArrayList<BookCopies> getBookCopies() {
	
	Connection connection = new DBConnection().getConnection();
	Statement statement = null;
	
	try {
		System.out.println("Creating statement for book copies...");
		statement = connection.createStatement();
		String sql;
		sql = "SELECT * FROM tbl_book_copies";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			BookCopies bookCopies = new BookCopies();
			Book book = new Book();
			LibraryBranch libraryBranch = new LibraryBranch();
			
			book.setBookId(rs.getInt("bookId"));
			bookCopies.setBook(book);
			libraryBranch.setBranchId(rs.getInt("branchId"));
			bookCopies.setLibraryBranch(libraryBranch);
			bookCopies.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopie.add(bookCopies);
		}
		rs.close();
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
	finally {
		try{
		connection.close();
		statement.close();	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
		return bookCopie;
}
	}
