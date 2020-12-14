package com.revature.repositories;

import java.util.HashMap;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface UserAccountDAO {
	
	public List<BankAccount> getAllBankAccounts();
	
	public List<UserAccount> getAllUserAccounts();
	
	public void saveNewLoginInfo(String username, String password);
	
	public HashMap<String,String> getLoginInfo();
	
	public UserAccount updateUserInfo(UserAccount u);
	
	public UserAccount getUserById(int id);
	
}
