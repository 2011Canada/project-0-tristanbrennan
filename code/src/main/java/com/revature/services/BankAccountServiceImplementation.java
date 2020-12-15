package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.MoneyTransfer;
import com.revature.models.UserAccount;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.UserAccountDAO;

public class BankAccountServiceImplementation implements BankAccountService {
	
	static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	BankAccountDAO ad; //this is what I'll use to implement the methods
	
	public BankAccountServiceImplementation(BankAccountDAO ad) {
		/*
		As the system, I reject invalid transactions.
		Ex:
		A withdrawal that would result in a negative balance.
		A deposit or withdrawal of negative money.
		2 points
		 */
		this.ad = ad;	
	}

	@Override
	public void createBankAccount(BankAccount b) {
		ad.createNewBankAccount(b);
	}

	@Override
	public double checkBalance(int accountId) {
		List<BankAccount> allAccounts = ad.getAllBankAccounts();
		
		for(BankAccount b : allAccounts) {
			if(b.getAccountId() == accountId) return b.getBalance();
		}
		
		return 0;
	}

	@Override
	public double makeDeposit(int accountId, double amount) {
		if(amount <= 0) {
			System.out.println("Invalid transaction.");
			return 0;
		}
		
		Logger e720Logger = LogManager.getLogger("com.revature.e720");
		List<BankAccount> allAccounts = ad.getAllBankAccounts();
		
		for(BankAccount b : allAccounts) {
			if(b.getAccountId() == accountId) {
				b.deposit(b.getBalance() + amount);
				ad.updateBankAccountInfo(b);
				e720Logger.info("Deposited $" + amount + " into account#" + accountId);
				return b.getBalance();
			}
		}
		
		return 0;
	}

	@Override
	public double makeWithdrawal(int accountId, double amount) {
		if(amount <= 0) {
			System.out.println("Invalid transaction.");
			return 0;
		}
		Logger e720Logger = LogManager.getLogger("com.revature.e720");
		List<BankAccount> allAccounts = ad.getAllBankAccounts();
		
		for(BankAccount b : allAccounts) {
			if(b.getAccountId() == accountId) {
				double result = b.withdraw(b.getBalance() + amount);
				ad.updateBankAccountInfo(b);
				e720Logger.info("Withdrew $" + amount + " from account#" + accountId);
				return result;
				/*
				 * This may not allow you to withdraw the full amount, depending on your
				 * balance.
				 */
				
			}
		}
		
		return 0;
	}

	@Override
	public List<BankAccount> viewAllCustomerAccounts(UserAccount target) {
		List<BankAccount> bankList = ad.getAllBankAccounts();
		List<BankAccount> resultList = new ArrayList<BankAccount>();
		
		for(BankAccount b : bankList) {
//			System.out.println("Found account belonging to user # " + b.getOwnerId());
			if(b.getOwnerId() == target.getUserId()) {
				resultList.add(b);
			}
		}
		
		return resultList;
	}

	@Override
	public void createMoneyTransfer(MoneyTransfer mt) {
		Logger e720Logger = LogManager.getLogger("com.revature.e720");
		ad.createMoneyTransfer(mt);
		e720Logger.info("New money transfer from User#" + mt.getOrigin()
		+ " to User#" + mt.getTarget() + " of amount $" + mt.getSum());
	}

	@Override
	public List<MoneyTransfer> getAllMoneyTransfers() {
		return ad.getAllMoneyTransfers();
	}

	@Override
	public List<MoneyTransfer> getAccountMoneyTransfers(BankAccount target) {
		List<MoneyTransfer> bankList = ad.getAllMoneyTransfers();
		List<MoneyTransfer> resultList = new ArrayList<MoneyTransfer>();
		
		for(MoneyTransfer b : bankList) {
//			System.out.println("Found account belonging to user # " + b.getOwnerId());
			if(b.getTarget().getAccountId() == target.getAccountId()) {
				resultList.add(b);
			}
		}
		
		return resultList;
	}

	@Override
	public BankAccount getBankAccountById(int id) {
		return ad.getAccountByIdNumber(id);
	}

	@Override
	public void resolveMoneyTransfer(int id) {
		ad.resolveMoneyTransfer(id);
	}

	@Override
	public void updateAccount(BankAccount b) {
		ad.updateBankAccountInfo(b);
//		List<BankAccount> allAccounts = ad.getAllBankAccounts();
//		
//		for(BankAccount b : allAccounts) {
//			if(b.getAccountId() == accountId) {
//				ad.updateBankAccountInfo(b);
//			}
//		}
		
	}

}
