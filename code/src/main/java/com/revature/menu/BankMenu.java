package com.revature.menu;

import java.util.Scanner;

import com.revature.services.BankAccountService;

public class BankMenu {
	
	Scanner userIn = new Scanner(System.in);;
	
	BankAccountService bas;
	
	public BankMenu(BankAccountService bas) {
		super();
		this.bas = bas;
	}
	
	public void loginPrompt() {
		
		/*
		As a user, I can login.
		2 points
		 */
		
		System.out.println("Please enter your username:");
		String username = manageUserInput();
		
		System.out.println("Please enter your password:");
		String password = manageUserInput();
		
		bas.login(username,password);
	}
	
	public void userAccountDashboard() {
		/*
		As a user, I can register for a customer account.
		3 points
		 */
		
		System.out.println("You haven't registered your customer account yet.");
		System.out.println("To register as a customer and start banking with ThinkBank, enter <customer>.");
		System.out.println("To register as an employee, enter <employee>. This will require approval from"
				+ " an existing employee.");
		
		String employeeOrCustomer = manageUserInput();
	}
	
	public void customerAccountDashboard() {
		
		/*
		As a customer, I can apply for a new bank account with a starting balance.
		3 points
		As a customer, I can view the balance of a specific account.
		1 point
		As a customer, I can make a withdrawal or deposit to a specific account.
		2 points
		As a customer, I can post a money transfer to another account.
		3 points
		As a customer, I can accept a money transfer from another account.
		2 points
		 */
		
		System.out.println("Welcome to the Customer Account Dashboard.");
		
		boolean customerHasBankAccount = false;
		
		if(customerHasBankAccount) {
			System.out.println("Here is a list of your active bank accounts:");
		}
		else {
			System.out.println("You have no active bank accounts:");
		}
		
		boolean customerHasPendingMoneyTransfer = false;
		
		if(customerHasPendingMoneyTransfer) {
			System.out.println("Here is a list of your pending money transfers:");
		}
		else {
			System.out.println("You have no pending money transfers:");
		}
		
		System.out.println("To make a transaction, enter one of the following commands:");
		System.out.println("To make a withdrawal, enter <withdraw> followed by the sum.");
		System.out.println("To make a deposit, enter <deposit> followed by the sum.");
		System.out.println("To accept all money transfers, enter <accept>.");
		System.out.println("To accept a single money transfer, enter <accept> followed by the id"
				+ " of the transfer.");
		System.out.println("To make a money transfer to another account, enter <transfer>, followed by the id"
				+ " of the originating account, followed by the sum, followed by the id of the target account.");
		
		//I may want to break that last one up to simplify
		
		String command = manageUserInput();
	}
	
	public void employeeAccountDashboard() {
		/*
		As an employee, I can approve or reject an account.
		2 points
		As an employee, I can view a customer's bank accounts.
		1 point
		A an employee, I can view a log of all transactions.
		2 points
		 */
		
		boolean eventsAwaitApproval = false;
		
		if(eventsAwaitApproval) {
			System.out.println("Here is a list of accounts that require your approval:");
			/*
			 * Creation of employee accounts requires approval.
			 * Creation of customer accounts only requires approval if the starting balance > 0.
			 */
		}
		else {
			System.out.println("No accounts require your approval.");
		}
		
		System.out.println("Enter one of the following commands:");
		System.out.println("To approve all accounts, enter <approve all>:");
		System.out.println("To approve a single account, enter <approve> followed by the id of the account:");
		System.out.println("To view a customer's bank accounts, enter <view> followed by the customer's"
				+ " id:");
		System.out.println("To view the transaction log, enter <log>:");
		
		String command = manageUserInput();
	}
	
	private String manageUserInput() {
		String input = this.userIn.nextLine();
		return input;
	}

}
