package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
	
	public static int NONE = 0;
	public static int CUSTOMER = 1;
	public static int EMPLOYEE = 2;
	
	private int userId;
	private String username;
	private String password;
	private int type = 0;
	private boolean verified = true;
	
	List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	public UserAccount(String username, String password, int type, int userId) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.userId = userId;
	}
	
	public void updateDatabase() {
		//update the database
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int i) {
		//Accepts only two legit values
		if(i == NONE || i == CUSTOMER || i == EMPLOYEE) {
			this.type = i;
		}
		
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public int getUserId() {
		return userId;
	}

}
