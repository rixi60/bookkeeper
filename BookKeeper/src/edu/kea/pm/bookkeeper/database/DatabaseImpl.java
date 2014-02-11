package edu.kea.pm.bookkeeper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import edu.kea.pm.bookkeeper.model.Book;

public class DatabaseImpl implements Database{
	
	private DatabaseHelper db;
	
	public DatabaseImpl(Context context){
		db = new DatabaseHelper(context);
	}
	
	@Override
	public Book getBookWithId(int id) {
		
        SQLiteDatabase database = db.getReadableDatabase();
        
        String[] columns = new String[] {
        		BookTable.TABLE_NAME+"."+BookTable.ID,
        		BookTable.AUTHORS,
        		BookTable.COMMENT,
        		BookTable.DESCRIPTION,
        		BookTable.IMAGE,
        		BookTable.ISBN,
        		BookTable.LANGUAGE,
        		BookTable.PAGE_COUNT,
        		BookTable.PUBLISHED,
        		BookTable.TITLE,
        		LoanTable.LOANER,
        };
        
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(BookTable.TABLE_NAME+" LEFT JOIN "+LoanTable.TABLE_NAME+" ON ("+BookTable.TABLE_NAME+"."+BookTable.ID+" = "+LoanTable.TABLE_NAME+"."+LoanTable.BOOK_ID+")");
        Cursor c = query.query(database, columns, BookTable.TABLE_NAME+"."+BookTable.ID+" = ?", new String[] {String.valueOf(id)}, null, null, BookTable.TITLE + " DESC");
        
        Book b = null;
        if (c.moveToFirst()) {
        	b = new Book();
        	b.setBook_id(c.getInt(c.getColumnIndex(BookTable.ID)));
        	b.setTitle(c.getString(c.getColumnIndex(BookTable.TITLE)));
        	b.setIsbn(c.getString(c.getColumnIndex(BookTable.ISBN)));
        	b.setAuthors(c.getString(c.getColumnIndex(BookTable.AUTHORS)));
        	b.setDescription(c.getString(c.getColumnIndex(BookTable.DESCRIPTION)));
        	b.setLanguage(c.getString(c.getColumnIndex(BookTable.LANGUAGE)));
        	b.setPageCount(c.getInt(c.getColumnIndex(BookTable.PAGE_COUNT)));
        	b.setPublished(c.getString(c.getColumnIndex(BookTable.PUBLISHED)));
        	b.setThumbnailURL(c.getString(c.getColumnIndex(BookTable.PUBLISHED)));
        	b.setComment(c.getString(c.getColumnIndex(BookTable.COMMENT)));
        	b.setLoaner(c.getString(c.getColumnIndex(LoanTable.LOANER)));
        }
        c.close();
        return b;
		
	}

	@Override
	public Cursor getAllBooks() 
	{
        String[] columns = new String[] {
        		BookTable.TABLE_NAME+"."+BookTable.ID,
        		BookTable.AUTHORS,
        		BookTable.COMMENT,
        		BookTable.DESCRIPTION,
        		BookTable.IMAGE,
        		BookTable.ISBN,
        		BookTable.LANGUAGE,
        		BookTable.PAGE_COUNT,
        		BookTable.PUBLISHED,
        		BookTable.TITLE,
        		LoanTable.LOANER,
        		LoanTable.TIMESTAMP
        };
        
        SQLiteDatabase database = db.getReadableDatabase();
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(BookTable.TABLE_NAME+" LEFT JOIN "+LoanTable.TABLE_NAME+" ON ("+BookTable.TABLE_NAME+"."+BookTable.ID+" = "+LoanTable.TABLE_NAME+"."+LoanTable.BOOK_ID+")");
        Cursor c = query.query(database, columns, null, null, null, null, BookTable.TITLE + " DESC");
     
        return c;
	}
 
	@Override
	public void saveBook(Book book) 
	{
		SQLiteDatabase database = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(BookTable.ISBN, book.getIsbn());
        values.put(BookTable.TITLE, book.getTitle());
        values.put(BookTable.DESCRIPTION, book.getDescription());
        values.put(BookTable.AUTHORS, book.getAuthors());
        values.put(BookTable.LANGUAGE, book.getLanguage());
        values.put(BookTable.PAGE_COUNT, book.getPageCount());
        values.put(BookTable.COMMENT, book.getComment());
        values.put(BookTable.IMAGE, book.getThumbnailURL());
        values.put(BookTable.PUBLISHED, book.getPublished());
		
		if (book.getBook_id() > 0) {
			// Update existing book:
			database.update(BookTable.TABLE_NAME, values, BookTable.ID + " = ?", new String [] { String.valueOf(book.getBook_id()) } );
			database.delete(LoanTable.TABLE_NAME, LoanTable.BOOK_ID + " = ?", new String [] { String.valueOf(book.getBook_id()) });
			if (!TextUtils.isEmpty(book.getLoaner())) {
	            ContentValues valuesLoaner = new ContentValues();
	            valuesLoaner.put(LoanTable.BOOK_ID, book.getBook_id());
	            valuesLoaner.put(LoanTable.LOANER, book.getLoaner());
	            database.insert(LoanTable.TABLE_NAME, null, valuesLoaner);
	        }
		} else {
			// Create new book:

	        // insert row
	        long book_id = database.insert(BookTable.TABLE_NAME, null, values);
	        
	        if (!TextUtils.isEmpty(book.getLoaner())) {
	            ContentValues valuesLoaner = new ContentValues();
	            valuesLoaner.put(LoanTable.BOOK_ID, book_id);
	            valuesLoaner.put(LoanTable.LOANER, book.getLoaner());
	            database.insert(LoanTable.TABLE_NAME, null, valuesLoaner);
	        }
		}
	}

	@Override
	public void deleteBook(Book book) 
	{
		SQLiteDatabase databse = db.getWritableDatabase();
		databse.delete(BookTable.TABLE_NAME, BookTable.ID + " = ?",
                new String[] { String.valueOf(book.getBook_id()) });
		databse.delete(LoanTable.TABLE_NAME, LoanTable.BOOK_ID + " = ?",
                new String[] { String.valueOf(book.getBook_id()) });
	}


}
