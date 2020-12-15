package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.BankAccount;
import com.revature.repositories.UserAccountDAO;

public class UserAccountServiceImplementationTest {
	
	private UserAccountServiceImplementation basi;
	private UserAccountDAO bad;
	
	@BeforeEach
	public void setupBasi() {
		this.bad = mock(UserAccountDAO.class);
		this.basi = new UserAccountServiceImplementation(this.bad);
	}
	
	@Test
	public void testSeeAllAccounts() {
		List<BankAccount> testValues = new ArrayList<BankAccount>();
		BankAccount a1 = new BankAccount(1,0.99);
		BankAccount a2 = new BankAccount(2,1000.00);
		BankAccount a3 = new BankAccount(3,0);
		testValues.add(a1);
		testValues.add(a2);
		testValues.add(a3);
		when(bad.getAllBankAccounts()).thenReturn(testValues);
		
		List<BankAccount> expectedValues = new ArrayList<BankAccount>();
		expectedValues.add(a1);
		expectedValues.add(a2);
		expectedValues.add(a3);
		
		assertEquals(expectedValues,bad.getAllBankAccounts());
	}

}
