package com.revature.services;

import com.revature.models.UserAccount;

public interface BankAccountService {
	
	public void createCustomerAccount(String username, String password);
	
	public void login(String username, String password);
	
	public void createBankAccount();
	
	public double checkBalance();
	
	public double makeDeposit(double amount);
	
	public double makeWithdrawal(double amount);
	
	public void approveAccount();
	
	public void rejectAccount();
	
	public void viewAllAccounts();
	
	public void viewAllCustomerAccounts(UserAccount target);
	
	public void createMoneyTransfer(UserAccount origin, UserAccount target);
	
	public void viewTransactionLog();
	
	void rejectInvalidTransaction();

}
