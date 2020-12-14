package com.revature.services;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface UserAccountService {
	
	public UserAccount updateUserInfo(UserAccount u);
	
	public UserAccount getUserById(int id);
	
	public void createCustomerAccount(String username, String password);
	
	public UserAccount login(String username, String password);
	
	public List<BankAccount> viewAllCustomerAccounts(UserAccount target);
	
	public List<UserAccount> viewAllUserAccounts();

}
