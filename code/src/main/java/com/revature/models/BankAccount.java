package com.revature.models;

public class BankAccount implements Displayable {

	private int accountId;
	private int ownerId;
	private double balance;
	private boolean verified;

	public BankAccount(int ownerId, double balance) {
		super();

		this.ownerId = ownerId;
		this.balance = balance;
		
		if(balance == 0) this.verified = true;
		else this.verified = false;
	}
	
	public BankAccount(int ownerId) {
		this(ownerId,0);
		
	}
	
	public BankAccount(int ownerId, double balance, int accountId, boolean verified) {
		this.ownerId = ownerId;
		this.balance = balance;
		this.verified = verified;
		this.accountId = accountId;
	}
	
	@Override
	public String display() {
		String v;
		if(verified) v = "verified";
		else v = "unverified";
		
		return "" + accountId + ", " + balance + ", " + v;
	}
	
	public double withdraw(double amount) {
		/*
		 * Attempts to withdraw money from the account. If there isn't enough, withdraws as much as
		 * possible. Returns the amount that was actually withdrawn, which may be less than the desired
		 * amount.
		 */
		
		if(amount <= balance) {
			balance = balance - amount;
			return amount;
		}
		else {
			double max_withdrawal = balance;
			balance = 0;
			return max_withdrawal;
		}
	}
	
	public double deposit(double amount) {
		balance = balance + amount;
		
		return balance;
	}

	public double getBalance() {
		return balance;
	}

	public int getOwnerId() {
		return ownerId;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	

}
