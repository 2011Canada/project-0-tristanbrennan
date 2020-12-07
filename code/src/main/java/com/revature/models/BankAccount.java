package com.revature.models;

import java.util.List;
import java.util.ArrayList;

public class BankAccount {
	
	private static List<BankAccount> all_accounts = new ArrayList<BankAccount>();
	private static int lowest_unused_id_num = 0;

	private int id_num;
	private double balance;

	public BankAccount(double starting_balance) {
		super();
		
		this.balance = starting_balance;
		
		//Set the user's id number automatically.
		id_num = lowest_unused_id_num;
		//Increment the counter so that id number can never be used again.
		lowest_unused_id_num++;
		
		//Add this account to the list of all BankAccounts.
		all_accounts.add(this);
	}
	
	public BankAccount() {
		this(0);
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId_num() {
		return id_num;
	}

	public static List<BankAccount> getAllAccounts() {
		return all_accounts;
	}

}
