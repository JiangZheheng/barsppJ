/**
 * Package for databases
 */
package com.version1_0.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author 40033364 This class will hold the databases we need for the app...
 */
public class ClubSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_BARS = "Bars";
	public static final String COLUMN_BAR_ID= "_id";
	public static final String COLUMN_BAR_NAME = "Name";
	public static final String COLUMN_BAR_LONG = "Longitude";
	public static final String COLUMN_BAR_LAT = "Latitude";

	private static final String DATABASE_NAME = "Bars.db";
	private static final int DATABASE_VERSION = 1;

	public ClubSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_BARS + "(" + COLUMN_BAR_ID
	      + " integer primary key autoincrement, " + COLUMN_BAR_NAME
	      + " text not null), " + COLUMN_BAR_LONG + " real, "  + COLUMN_BAR_LAT + " real;";
	  
	  @Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARS);
			onCreate(db);
		}

}
