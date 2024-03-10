package com.company.model;

public class User {
	private String fullName;
	private String username;
	private String password;
	
	public User(String fullName, String username, String password) {
	    this.fullName = fullName;
	    this.username = username;
	    this.password = password;
	}
	
	 // Getter para fullName
    public String getFullName() {
        return fullName;
    }

    // Setter para fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter para username
    public String getUsername() {
        return username;
    }

    // Setter para username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para password
    public String getPassword() {
        return password;
    }

    // Setter para password
    public void setPassword(String password) {
        this.password = password;
    } 
}
