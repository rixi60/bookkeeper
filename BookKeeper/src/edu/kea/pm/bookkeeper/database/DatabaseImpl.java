package edu.kea.pm.bookkeeper.database;

import java.util.List;

import edu.kea.pm.bookkeeper.model.Book;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseImpl implements Database{
	
	private DatabaseHelper db;
	
	public DatabaseImpl(Context context){
		db = new DatabaseHelper(context);
	}
	
	@Override
	public Book getBookWithId(String id) {
		return db.getBook(Long.parseLong(id));
	}

	@Override
	public List<Book> getAllBooks() {
		return db.getAllBooks();
	}
 
	@Override
	public void saveBook(Book book) {
		db.updateBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		db.deleteBook(book.getBook_id());
	}


}
