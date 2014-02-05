package edu.kea.pm.bookkeeper.database;

import java.util.List;

import android.database.Cursor;

import edu.kea.pm.bookkeeper.model.*;

public interface Database
{
	
	//Getters
	public Book getBookWithId(String id);
	public Cursor getAllBooks();
	public List<Book> getBooksWithISBN(String isbn);
	public List<Book> getBooksWithAuthor(String authorName);
	public List<Book> getBooksWithTitle(String title);
	public List<Book> getLoanedBooks();
	public List<Book> getBooksLoanedByPerson(String personName);
	public List<Book> getBooksWithPublishedYear(int year);		

	//Setters:
	public void addBook(Book book);
	public void saveBook(Book book);
	
	
}
