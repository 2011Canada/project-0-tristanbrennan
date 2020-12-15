package com.revature.services;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.MoneyTransfer;
import com.revature.models.UserAccount;

public interface BankAccountService {
	
	public void createBankAccount(BankAccount b);
	
	public double checkBalance(int accountId);
	
	public double makeDeposit(int accountId,double amount);
	
	public double makeWithdrawal(int accountId,double amount);
	
	public void createMoneyTransfer(MoneyTransfer mt);
	
	public List<MoneyTransfer> getAllMoneyTransfers();
	
	public List<MoneyTransfer> getAccountMoneyTransfers(BankAccount target);
	
	public void resolveMoneyTransfer(int id);

	List<BankAccount> viewAllCustomerAccounts(UserAccount target);
	
	public BankAccount getBankAccountById(int id);

	void updateAccount(BankAccount b);

}
