package edu.kea.pm.bookkeeper.database;

public class LoanTable {
	public static final String TABLE_NAME = "loaner";
	
	public static final String ID = "_id";
	public static final String BOOK_ID = "book_id";
	public static final String LOANER = "loaner";
	public static final String TIMESTAMP = "timestamp";
	
	// id int
	// bookid int
	// loaner String
	
    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" 
    		+ ID + " INTEGER PRIMARY KEY," 
    		+ BOOK_ID + " TEXT,"
    		+ LOANER + " TEXT," 
            + TIMESTAMP + " DATETIME default CURRENT_DATE,"
            + "FOREIGN KEY(" + BOOK_ID + ") REFERENCES " + BookTable.TABLE_NAME + "(" + BookTable.ID + "))";
}
