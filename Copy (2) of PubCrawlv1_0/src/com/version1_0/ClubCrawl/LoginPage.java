package com.version1_0.ClubCrawl;

/**
 * Student Name: Jiang Zhe Heng Student 
 *  This class inherits
 * ActionBarActivity class
 * 
 */
import com.version1_0.ClubCrawl.R;
import com.version1_0.sqlite.CommentsDataSource;
import com.version1_0.sqlite.RegisteredUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class LoginPage extends Activity {
	private CommentsDataSource datasource;
	RegisteredUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Invoke the onCreaate constructor of the parent, savedInstanceState
		// mean to save the current state
		super.onCreate(savedInstanceState);
		// Set the content view for the activity linking with the XML file
		setContentView(R.layout.login_page);
		datasource = new CommentsDataSource(this);
		datasource.open();
		// Find the button of "Login Now"
		Button buttonLogin = (Button) findViewById(R.id.loginNow);
		
		Button buttonRegister = (Button) findViewById(R.id.register);
		
		buttonRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LoginPage.this,Register.class);
				startActivity(intent);
			}
		});
		// Register Click event with this button
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			// Find the text of "leftNumber" which shows the left numbers of
			// chance to try again
			TextView leftNumber = (TextView) findViewById(R.id.leftNumber);
			// Find the edit text of "editUsername" where people can input the
			// user name
			TextView editUserName = (TextView) findViewById(R.id.editUserName);
			// Find the edit text of "editPassword" where people can input the
			// password
			TextView editPassword = (TextView) findViewById(R.id.editPassword);

			@Override
			public void onClick(View v) {
				if (editUserName.getText().length() != 0
						&& editPassword.getText().length() != 0) {
					user = datasource.getUserInformation(editUserName.getText()
							.toString(), editPassword.getText().toString());
					// If both of user name and password are correct
					if (user != null) {
						if (user.getUserName().equals(
								editUserName.getText().toString())
								&& user.getPassword().equals(
										editPassword.getText().toString())) {
							Intent intent = new Intent();
							intent.setAction("android.intent.action.DrinkSelection");
							intent.addCategory(Intent.CATEGORY_DEFAULT);
							startActivity(intent);
						} else {
							Toast.makeText(getBaseContext(),
									"Wrong Credentials", Toast.LENGTH_SHORT)
									.show();
						}

						// If any of user name or password is incorrect and the
						// number of chance to try is only one
					} else if (leftNumber.getText().toString().equals("1")) {
						// Find the button of "Login Now"
						Button button = (Button) findViewById(R.id.loginNow);
						// Set the button is unable to use
						button.setEnabled(false);
						// Set the left number to 0
						leftNumber.setText("0");
						// Set the background color of the left number to red
						leftNumber.setBackgroundColor(Color.RED);
						// use makeText method of toast to show unsuccessful
						// message
						Toast.makeText(getBaseContext(), "Wrong Credentials",
								Toast.LENGTH_SHORT).show();
						// If system still permit user have some chances to try
					} else {
						// Get the text of leftNumber and change the type of it
						// from
						// CharSequence to integer
						int temp = Integer.parseInt(leftNumber.getText()
								.toString());
						// Make the number of chance decrease and change the
						// type to
						// String, then set it to the text of leftNumber
						leftNumber.setText(String.valueOf(temp - 1));
						// Set the background color of the left number to red
						leftNumber.setBackgroundColor(Color.RED);
						// use makeText method of toast to show unsuccessful
						// message
						Toast.makeText(getBaseContext(), "Wrong Credentials",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(),
							"Please complete all information",
							Toast.LENGTH_SHORT).show();
				}
			}

		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		datasource.open();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		datasource.close();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
