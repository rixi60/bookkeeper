package edu.kea.pm.bookkeeper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseImpl {

	SQLiteDatabase db;
	
	public DatabaseImpl(Context context){
		db = new DatabaseHelper(context).getWritableDatabase();
	}
}
