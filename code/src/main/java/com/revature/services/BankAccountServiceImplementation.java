package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.UserAccount;
import com.revature.repositories.BankAccountDAO;

public class BankAccountServiceImplementation implements BankAccountService {
	
	static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	BankAccountDAO bad; //this is what I'll use to implement the methods
	
	public BankAccountServiceImplementation(BankAccountDAO bad) {
		/*
		As the system, I reject invalid transactions.
		Ex:
		A withdrawal that would result in a negative balance.
		A deposit or withdrawal of negative money.
		2 points
		 */
		this.bad = bad;	
	}

	@Override
	public void createCustomerAccount(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String username, String password) {
		//check login credentials
		
		e720Logger.info(username + " logged in.");
	}

	@Override
	public void createBankAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double checkBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double makeDeposit(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double makeWithdrawal(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void approveAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllAccounts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllCustomerAccounts(UserAccount target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMoneyTransfer(UserAccount origin, UserAccount target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectInvalidTransaction() {
		// TODO Auto-generated method stub
		
	}

}
