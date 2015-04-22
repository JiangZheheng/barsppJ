package com.version1_0.sqlite;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommentsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private RUSQLiteHelper dbHelper;
	private String[] allColumns = { RUSQLiteHelper.COLUMN_USERNAME,
			RUSQLiteHelper.COLUMN_PASSWORD };// define the format of string
												// allColumns

	public CommentsDataSource(Context context) {
		// instantiate the RUSQLiteHelper class
		dbHelper = new RUSQLiteHelper(context);
	}

	public void open() throws SQLException {
		// open the database for reading and writing
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void createRegisteredUser(String userName, String password) {
		// instantiate the class ContentValues
		ContentValues values = new ContentValues();

		// initialise values to be inserted to the database
		values.put(RUSQLiteHelper.COLUMN_USERNAME, userName);
		values.put(RUSQLiteHelper.COLUMN_PASSWORD, password);

		// Returns a new InsertQuery object for the active database
		database.insert(RUSQLiteHelper.TABLE_COMMENTS, null, values);

	}

	public void deleteComment(RegisteredUser user) {
		String userName = user.getUserName();

		// printf(). should use log.w() instead
		System.out.println("Comment deleted with id: " + userName);
		// delete records in the database
		database.delete(RUSQLiteHelper.TABLE_COMMENTS,
				RUSQLiteHelper.COLUMN_USERNAME + " = " + userName, null);
	}

	public List<RegisteredUser> getAllUsers() {
		// instantiate List<Commnet>
		// can we change it to: List<Comment> comments = new
		// ArrayList<Comment>(this);
		List<RegisteredUser> users = new ArrayList<RegisteredUser>();

		Cursor cursor = database.query(RUSQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			RegisteredUser comment = cursorToUser(cursor);
			users.add(comment);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return users;
	}

	public RegisteredUser getUserInformation(String userName, String passWord) {
		RegisteredUser user=new RegisteredUser();
		String[] args={userName};

		Cursor cursor = database.query(RUSQLiteHelper.TABLE_COMMENTS,
				allColumns, RUSQLiteHelper.COLUMN_USERNAME + " = ?",
				args, null, null, null);
		Log.i(String.valueOf(cursor.getCount()),String.valueOf(cursor.getCount()));
		if(cursor.getCount()>0){
			cursor.moveToFirst();
			user.setUserName(cursor.getString(0));
			user.setPassword(cursor.getString(1));
			cursor.close();
			return user;
		}else{
			cursor.close();
			return null;
		}
		
		
	}

	private RegisteredUser cursorToUser(Cursor cursor) {
		RegisteredUser user = new RegisteredUser();
		user.setUserName(cursor.getString(0));
		// set the comment and return the column values as strings
		user.setPassword(cursor.getString(1));
		return user;
	}
}
