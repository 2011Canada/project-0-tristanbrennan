package com.revature.repositories;

import com.revature.models.BankAccount;

public interface BankAccountDAO {
	
	public BankAccount getAccountByIdNumber(int id_number);
	
	public BankAccount getAccountByUsername(String username);

}
