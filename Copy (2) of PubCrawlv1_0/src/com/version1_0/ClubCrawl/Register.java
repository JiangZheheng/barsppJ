package com.version1_0.ClubCrawl;

/**
 * the class represents register activity, new user can register on this activity
 * @author Jiang Zhe Heng
 */
import com.version1_0.sqlite.CommentsDataSource;
import com.version1_0.sqlite.RegisteredUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	private CommentsDataSource datasource;
	private RegisteredUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		datasource = new CommentsDataSource(this);
		datasource.open();

		Button button = (Button) findViewById(R.id.B_submit);
		// Register Click event with this button
		button.setOnClickListener(new View.OnClickListener() {

			// Find the edit text of "editUsername" where people can input the
			// user name
			TextView editUserName = (TextView) findViewById(R.id.ET_editUserName);
			// Find the edit text of "editPassword" where people can input the
			// password
			TextView editPassword = (TextView) findViewById(R.id.ET_editPassword);

			TextView editConfirmPassword = (TextView) findViewById(R.id.ET_editConfirmPassword);

			@Override
			public void onClick(View v) {
				if (editUserName.getText().length() != 0
						&& editPassword.getText().length() != 0
						&& editConfirmPassword.getText().length() != 0) {

					if (editPassword.getText().toString()
							.equals(editConfirmPassword.getText().toString())) {
						user = datasource.getUserInformation(editUserName
								.getText().toString(), editPassword.getText()
								.toString());
						if (user == null) {
							datasource.createRegisteredUser(editUserName
									.getText().toString(), editPassword
									.getText().toString());
							Toast.makeText(getBaseContext(),
									"Sucess",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getBaseContext(),
									"This userName has been registered, please try another",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getBaseContext(),
								"Two passwords are not the same",
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public Register() {

	}

}
