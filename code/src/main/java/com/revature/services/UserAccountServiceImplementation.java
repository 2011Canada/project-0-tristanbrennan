package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.repositories.UserAccountDAO;

public class UserAccountServiceImplementation implements UserAccountService {
	
	static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	UserAccountDAO ad; //this is what I'll use to implement the methods
	
	public UserAccountServiceImplementation(UserAccountDAO ad) {
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
	public UserAccount login(String username, String password) {
		Logger e720Logger = LogManager.getLogger("com.revature.e720");
		
		List<UserAccount> listOfAccounts = ad.getAllUserAccounts();
		
		UserAccount usernameMatch = null;
		
		for(int i=0;i<listOfAccounts.size();i++) {
			if(listOfAccounts.get(i).getUsername().equals(username)) {
				usernameMatch = listOfAccounts.get(i);
			}
		}
		
		if(usernameMatch.getPassword().equals(password)){
			e720Logger.info(username + " logged in.");
			return usernameMatch;
		}
		else {
			e720Logger.info(username + " entered an incorrect password.");
			return null;
		}
		/*
		 * If the return value is null, the user is not found.
		 */
		
	}

	@Override
	public List<BankAccount> viewAllCustomerAccounts(UserAccount target) {
		List<BankAccount> bankList = ad.getAllBankAccounts();
		List<BankAccount> resultList = new ArrayList<BankAccount>();
		
		for(BankAccount b : bankList) {
			if(b.getOwnerId() == target.getUserId()) {
				resultList.add(b);
			}
		}
		
		return resultList;
	}

	@Override
	public UserAccount updateUserInfo(UserAccount u) {
		ad.updateUserInfo(u);
		return getUserById(u.getUserId());
	}

	@Override
	public void createCustomerAccount(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserAccount getUserById(int id) {
		List<UserAccount> listOfAccounts = ad.getAllUserAccounts();
		
		for(int i=0;i<listOfAccounts.size();i++) {
			if(listOfAccounts.get(i).getUserId() == id) {
				return listOfAccounts.get(i);
			}
		}
		return null;
	}

	@Override
	public List<UserAccount> viewAllUserAccounts() {
		return ad.getAllUserAccounts();
	}

}
