package com.revature.models;

public class Account {

	private String username;
	private String password;

	private double balance = 0;

	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public double Withdraw(double amount) {
		/*
		 * Attempts to withdraw money from the account. If there isn't enough, withdraws as much as
		 * possible. Returns the amount that was actually withdrawn, which may be less than the desired
		 * amount.
		 */
		
		if(amount >= balance) {
			balance = balance - amount;
			return amount;
		}
		else {
			double max_withdrawal = balance;
			balance = 0;
			return max_withdrawal;
		}
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
