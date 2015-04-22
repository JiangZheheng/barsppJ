package com.version1_0.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RUSQLiteHelper extends SQLiteOpenHelper {


	//initialise the table of the database
	public static final String TABLE_COMMENTS = "registeredUser";
	public static final String COLUMN_USERNAME = "userName";
	public static final String COLUMN_PASSWORD = "password";
	private static final String DATABASE_NAME = "registeredUser.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "( " + COLUMN_USERNAME
			+ " text not null primary key, " + COLUMN_PASSWORD
			+ " text not null);";
	
	public RUSQLiteHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(db);
	}

}
