package com.version1_0.sqlite;

public class RegisteredUser {
	
	private String UserName;
	
	private String Password;

	public RegisteredUser() {

	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
