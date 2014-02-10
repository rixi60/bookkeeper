package edu.kea.pm.bookkeeper.database;

import java.util.ArrayList;
import java.util.List;

import edu.kea.pm.bookkeeper.model.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "bookKeeper";
 
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(BookTable.CREATE_TABLE);
        db.execSQL(LoanTable.CREATE_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + BookTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LoanTable.TABLE_NAME);
        // create new tables
        onCreate(db);
    }

    /* CRUD GOES FROM HERE */ 
    
    /* C - CREATE statements */
    
    /*
    * Creating a book
    * Loaner b¿r v¾re id til loan tabellen
    */
    public void createBook(Book b) {
    	
        SQLiteDatabase db = this.getWritableDatabase();
        
        
        ContentValues values = new ContentValues();
        values.put(BookTable.ISBN, b.getIsbn());
        values.put(BookTable.TITLE, b.getTitle());
        values.put(BookTable.DESCRIPTION, b.getDescription());
        values.put(BookTable.LANGUAGE, b.getLanguage());
        values.put(BookTable.PAGE_COUNT, b.getPageCount());
        values.put(BookTable.COMMENT, b.getComment());
        values.put(BookTable.IMAGE, b.getThumbnailURL());
        values.put(BookTable.PUBLISHED, b.getPublished());
        
        // insert row
        long book_id = db.insert(BookTable.TABLE_NAME, null, values);

        if (!TextUtils.isEmpty(b.getLoaner())) {
            ContentValues valuesLoaner = new ContentValues();
            valuesLoaner.put(LoanTable.BOOK_ID, book_id);
            valuesLoaner.put(LoanTable.LOANER, b.getLoaner());
            db.insert(LoanTable.TABLE_NAME, null, valuesLoaner);
        }
        
        
    }
    
    /* R - READ statements */
    
    /*
     * get single book
     */
    public Book getBook(long book_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + BookTable.TABLE_NAME + " WHERE "
                + BookTable.ID + " = " + book_id;
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
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
     
        return b;
    }
    
    /*
     * getting all books
     * */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT  * FROM " + BookTable.TABLE_NAME;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
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
    
    /*
     * get single loan
     */
    public String getLoan(long loan_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + LoanTable.TABLE_NAME + " WHERE "
                + LoanTable.ID + " = " + loan_id;
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        //Loan l = new Loan();
        //l.setLoan_id(c.getLong(c.getColumnIndex(LoanTable.ID)));
        String loaner = c.getString((c.getColumnIndex(LoanTable.LOANER)));
     
        return loaner;
    }
    
    /* U - UPDATE statements */
    
    /*
     * Updating a book
     */
    public int updateBook(Book b) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(BookTable.ISBN, b.getAuthors());
        values.put(BookTable.TITLE, b.getAuthors());
        values.put(BookTable.DESCRIPTION, b.getAuthors());
        values.put(BookTable.LANGUAGE, b.getAuthors());
        values.put(BookTable.PAGE_COUNT, b.getAuthors());
        values.put(BookTable.COMMENT, b.getAuthors());
        values.put(BookTable.IMAGE, b.getThumbnailURL());
        values.put(BookTable.PUBLISHED, b.getPublished());
     
        // updating row
        return db.update(BookTable.TABLE_NAME, values, BookTable.ID + " = ?",
                new String[] { String.valueOf(b.getBook_id()) });
    }
    
    /* D - DELETE statements */
    
    /**
     * Deleting a book
     */
    public void deleteBook(long book_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BookTable.TABLE_NAME, BookTable.ID + " = ?",
                new String[] { String.valueOf(book_id) });
    }
}
