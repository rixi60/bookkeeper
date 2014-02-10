package edu.kea.pm.bookkeeper.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.kea.pm.bookkeeper.model.Book;

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
	public List<Book> getAllBooks() 
	{
		List<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT  * FROM " + BookTable.TABLE_NAME;
     
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Book b = new Book();
                b.setBook_id(c.getLong(c.getColumnIndex(BookTable.ID)));
                b.setIsbn(c.getString(c.getColumnIndex(BookTable.ISBN)));
                b.setAuthors(c.getString(c.getColumnIndex(BookTable.ID)));
                b.setDescription(c.getString(c.getColumnIndex(BookTable.ID)));
                b.setLanguage(c.getString(c.getColumnIndex(BookTable.ID)));
                b.setPageCount(c.getInt(c.getColumnIndex(BookTable.PAGE_COUNT)));
                b.setPublished(c.getString(c.getColumnIndex(BookTable.PUBLISHED)));
                b.setThumbnailURL(c.getString(c.getColumnIndex(BookTable.PUBLISHED)));
                b.setComment(c.getString(c.getColumnIndex(BookTable.COMMENT)));
     
                // adding to todo list
                books.add(b);
            } while (c.moveToNext());
        }
     
        return books;
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
