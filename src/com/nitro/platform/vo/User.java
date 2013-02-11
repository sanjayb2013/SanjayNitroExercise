package com.nitro.platform.vo;

public class User {

	// Note : email used as UserID for simplicity here
	private final String userID;
	private final String passwd;

	private String firstName;
	private String lastName;
	
	private String status;

	public User(String email, String passwd) {
		this.userID = email;
		this.passwd = passwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserID() {
		return userID;
	}

	public String getPasswd() {
		return passwd;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
