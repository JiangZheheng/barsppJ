package com.version1_0.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager {
	private DbHelper mDbHelper;
	private Context mContext;
	private SQLiteDatabase mDb;
	
	
	private final class DbHelper extends SQLiteOpenHelper {
		
		public static final String DB_NAME = "Drink.db";
		public static final int SCHEMA_VERSION = 1;
		
		public static final String SQL_CREATE_TABLE =
				"CREATE TABLE bars ("
	      + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
	      + "barName text not null), " + "barLong real, "  + "barLat real," + 
	      ")";
		
		public static final String SQL_DROP_TABLE =
				"DROP TABLE 8IF EXISTS bars";

		public DbHelper(Context context) {
			super(context, DB_NAME, null, SCHEMA_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_TABLE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DROP_TABLE);
				onCreate(db);
			
		}
		
	}
	public DbManager (Context context){
		mContext = context;
	}
	
	public void open(){
		mDbHelper = new DbHelper (mContext);
		mDb =mDbHelper.getWritableDatabase();
	}

}
