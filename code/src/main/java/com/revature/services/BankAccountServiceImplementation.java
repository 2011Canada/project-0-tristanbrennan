package com.revature.services;

import com.revature.repositories.BankAccountDAO;

public class BankAccountServiceImplementation implements BankAccountService {
	
	BankAccountDAO bad; //this is what I'll use to implement the methods
	
	public BankAccountServiceImplementation(BankAccountDAO bad) {
		this.bad = bad;
	}

	@Override
	public double checkBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double makeDeposit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double makeWithdrawal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
