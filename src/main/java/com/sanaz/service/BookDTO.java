package com.sanaz.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanaz.model.Author;
import com.sanaz.model.Book;
import com.sanaz.model.Publisher;
import com.sanaz.util.DBConnection;

public class BookDTO {

	ArrayList<Book> books = new ArrayList<Book>();

	public ArrayList<Book> getBooks() {

		Connection connection = new DBConnection().getConnection();
		Statement statement = null;
		try {
			System.out.println("Creating statement for book...");
			statement = connection.createStatement();
			String sql;
			sql = "SELECT * FROM tbl_book inner join tbl_author on tbl_author.authorId = tbl_book.authId";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				Author author = new Author();
				Publisher publisher = new Publisher();
				
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				author.setAuthorId(rs.getInt("authId"));
				author.setAuthorName(rs.getString("authorName"));
				book.setAuthor(author);
				publisher.setPubliserId(rs.getInt("pubId"));
				book.setPublisher(publisher);
				books.add(book);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	public Book getBookById(int bookId) {

		Connection connection = new DBConnection().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Book book = new Book();

		try {
			preparedStatement = 
					connection.prepareStatement("SELECT bookId, title FROM tbl_book WHERE bookId = ?");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return book;

	}

}
