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

}
