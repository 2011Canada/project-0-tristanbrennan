package com.revature.repositories;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.MoneyTransfer;

public interface BankAccountDAO {

	public BankAccount getAccountByIdNumber(int id_number);
	
	public void createNewBankAccount(BankAccount b);

	void updateBankAccountInfo(BankAccount b);

	List<BankAccount> getAllBankAccounts();
	
	public void createMoneyTransfer(MoneyTransfer mt);
	
	public List<MoneyTransfer> getAllMoneyTransfers();
	
	public void resolveMoneyTransfer(int id);

}
