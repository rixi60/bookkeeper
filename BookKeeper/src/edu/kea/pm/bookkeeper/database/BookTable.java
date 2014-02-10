package edu.kea.pm.bookkeeper.database;

public class BookTable {
	// TABLE NAME
	public static final String TABLE_NAME = "book";
	
	public static final String ID = "_id";
	public static final String ISBN = "isbn";
	public static final String TITLE = "title";
	public static final String AUTHORS = "authors";
	public static final String DESCRIPTION = "desc";
	public static final String LANGUAGE = "lang";
	public static final String PAGE_COUNT = "page_count";
	public static final String COMMENT = "comment";
	public static final String PUBLISHED = "published";
	public static final String IMAGE = "img_url";
	
    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" 
    		+ ID + " INTEGER PRIMARY KEY," 
    		+ ISBN + " TEXT,"
    		+ TITLE + " TEXT," 
    		+ AUTHORS + " TEXT,"
    		+ DESCRIPTION + " TEXT,"
    		+ LANGUAGE + " TEXT,"
            + PAGE_COUNT + " INTEGER," 
            + COMMENT + " TEXT,"
            + IMAGE + " TEXT,"
            + PUBLISHED + " TEXT)";
}
