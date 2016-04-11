package com.sanaz.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanaz.model.Book;
import com.sanaz.model.BookLoans;
import com.sanaz.model.Borrower;
import com.sanaz.model.LibraryBranch;
import com.sanaz.util.DBConnection;

public class BookLoansDTO {
	
	ArrayList<BookLoans> bookLoans = new ArrayList<BookLoans>();
	
	public ArrayList<BookLoans> getBookLoans(){
		Connection connection = new DBConnection().getConnection();
		Statement statement = null;
		
		try {
			System.out.println("Creating statement for book loans...");
			statement = connection.createStatement();
			String sql;
			sql = "SELECT * FROM tbl_book_loans";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				BookLoans bookLoan = new BookLoans();
				Book book = new Book();
				LibraryBranch libraryBranch = new LibraryBranch();
				Borrower borrower = new Borrower();
				
				book.setBookId(rs.getInt("bookId"));
				bookLoan.setBook(book);
				libraryBranch.setBranchId(rs.getInt("branchId"));
				bookLoan.setLibraryBranch(libraryBranch);
				borrower.setCardNo(rs.getInt("cardNo"));	
				bookLoan.setBorrower(borrower);
				bookLoan.setDateOut(rs.getDate("dateOut"));
				bookLoan.setDueDate(rs.getDate("dueDate"));
				bookLoans.add(bookLoan);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return bookLoans;	
	}

}
