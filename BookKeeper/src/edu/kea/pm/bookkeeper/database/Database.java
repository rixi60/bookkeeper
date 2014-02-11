package edu.kea.pm.bookkeeper.database;

import android.database.Cursor;
import edu.kea.pm.bookkeeper.model.Book;

public interface Database
{
	
	//Getters
	public Book getBookWithId(int id);
	public Cursor getAllBooks();

	//Setters:
	public void saveBook(Book book);
	
	public void deleteBook(Book book);
	
	
}
