package com.revature.menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.BankAccount;
import com.revature.models.MoneyTransfer;
import com.revature.models.UserAccount;
import com.revature.services.BankAccountService;
import com.revature.services.UserAccountService;

public class BankMenu {
	
	private int mode = 0;
	UserAccount activeUser = null;
	
	Scanner userIn = new Scanner(System.in);
	UserAccountService uas;
	BankAccountService bas;
	
	public BankMenu(BankAccountService bas, UserAccountService uas) {
		super();
		this.bas = bas;
		this.uas = uas;
	}
	
	public void beginMenuLoop() {
		//beginning of loop
		while(true) {
			if(activeUser != null) mode = 1;
			else mode = 0;
			
			
			if(mode == 0) {
				frontPrompt();
			}
			else if(mode == 1) {
				userAccountDashboard();
			}
			
			
		}
		//end of loop
	}
	
	public void frontPrompt() {
		System.out.println("To login as an existing user, enter <login>");
		System.out.println("To create a new account, enter <new>");
		
		String userInput = manageUserInput();
		
		if(userInput.equals("login")) {
			activeUser = loginPrompt();
		}
		else if(userInput.equals("new")) {
			createPrompt();
			beginMenuLoop();
		}
	}
	
	public void createPrompt() {
		System.out.println("Enter new username:");
		String u = manageUserInput();
		System.out.println("Enter new password:");
		String p = manageUserInput();
		uas.createCustomerAccount(u, p);
	}
	
	public UserAccount loginPrompt() {
		
		/*
		As a user, I can login.
		2 points
		 */
		
		System.out.println("Please enter your username:");
		String username = manageUserInput();
		
		System.out.println("Please enter your password:");
		String password = manageUserInput();
		
		return uas.login(username,password);
	}
	
	public void userAccountDashboard() {
		/*
		As a user, I can register for a customer account.
		3 points
		 */
		
		String userInput;
		
		if(activeUser == null) {
			mode = 0;
			return;
		}
		else if(activeUser.getType() == UserAccount.NONE) {
			System.out.println("You haven't registered your customer account yet.");
			System.out.println("To register as a customer and start banking with ThinkBank, enter <customer>.");
//			System.out.println("To register as an employee, enter <employee>. This will require approval from"
//					+ " an existing employee.");
			
			userInput = manageUserInput();
			if(userInput.equals("customer")){
				activeUser.setType(UserAccount.CUSTOMER);
				uas.updateUserInfo(activeUser);
			}
//			else if(userInput.equals("employee")) {
//				activeUser.setType(UserAccount.EMPLOYEE);
//				uas.updateUserInfo(activeUser);
//				System.out.println("Please wait for another employee to verify your account.");
//			}
		}
		else if(activeUser.getType() == (UserAccount.CUSTOMER)) {
			customerAccountDashboard();
		}
		else if(activeUser.getType() == (UserAccount.EMPLOYEE)) {
			employeeAccountDashboard();
		}
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
		System.out.println("#######################");
		System.out.println("Welcome to the Customer Account Dashboard.");
		System.out.println("Your User ID is # " + activeUser.getUserId());
		
		System.out.println("To make a new bank account, enter <new> followed by the desired starting balance.");
		System.out.println("Any starting balance other than 0 will require approval by an employee.");
		
		List<BankAccount> customerAccounts = bas.viewAllCustomerAccounts(activeUser);
		List<MoneyTransfer> customerTransfers = new ArrayList<MoneyTransfer>();
		
		if(customerAccounts.size() != 0) {
			System.out.println("Here is a list of your active bank accounts:");
			for(BankAccount b : customerAccounts) {
				System.out.println(b.display());
				List<MoneyTransfer> tList = bas.getAccountMoneyTransfers(b);
				
				for(MoneyTransfer m : tList) {
					if(m.getSum() != 0) {
						System.out.println("Money Transfer: " + m.display());
						customerTransfers.add(m);
					}
				}
			}
			System.out.println("To make a transaction, enter one of the following commands:");
			System.out.println("To make a withdrawal, enter <withdraw> followed by the account id followed by the sum.");
			System.out.println("To make a deposit, enter <deposit> followed by the account id followed by the sum.");
			System.out.println("To make a money transfer to another account, enter <transfer>, followed by the id"
					+ "\n" + "of the originating account, followed by the sum, followed by the id of the target"
					+ "\n" +"account.");
			System.out.println("To accept all money transfers, enter <accept>.");
			System.out.println("To accept a single money transfer, enter <accept> followed by the id"
					+ "\n" + "of the transfer.");
		}
		else {
			System.out.println("You have no active bank accounts.");
		}
		
		//I may want to break that last one up to simplify
		
		String command = manageUserInput();
		
		String[] splitCommand = command.split("\\s+");
		
		 if(splitCommand.length == 1) {
			 if(splitCommand[0].equals("accept")) {
				 for(MoneyTransfer m : customerTransfers) {
					 
					 double sum = m.getOrigin().withdraw(m.getSum());
					 bas.makeWithdrawal(m.getOrigin().getAccountId(), sum);
					 bas.makeDeposit(m.getTarget().getAccountId(), sum);
					
					 bas.resolveMoneyTransfer(m.getId());
				}
				 System.out.println("Accepted all transfers.");
			 }
			 else if(splitCommand[0].equals("logout")) {
				 activeUser = null;
				 beginMenuLoop();
			 }
		 }
		 else if(splitCommand.length == 2) {
			 if(splitCommand[0].equals("accept")) {
				 int val = Integer.parseInt((splitCommand[1]));
				 for(MoneyTransfer m : customerTransfers) {
					 if(m.getId() == val) {
						 double sum = m.getOrigin().withdraw(m.getSum());
						 bas.makeWithdrawal(m.getOrigin().getAccountId(), sum);
						 bas.makeDeposit(m.getTarget().getAccountId(), sum);
						
						 bas.resolveMoneyTransfer(m.getId());
					 }
				 }
				 System.out.println("Accepted money transfer " + val);
			 }
			 else if(splitCommand[0].equals("new")) {
				 double val = Double.parseDouble((splitCommand[1]));
				 BankAccount b = new BankAccount(activeUser.getUserId(), val);
				 bas.createBankAccount(b);
				 System.out.println("Created account with starting balance $" + val);
			 }
		 }
			 else if(splitCommand.length == 3) {
				 double val = Double.parseDouble((splitCommand[2]));
				 int id = Integer.parseInt((splitCommand[1]));
				 
				 if(splitCommand[0].equals("withdraw")) {
					 bas.makeWithdrawal(id, val);
					 if(val > 0) System.out.println("Withdrew $" + val);
				 }
				 else if(splitCommand[0].equals("deposit")) {
					 bas.makeDeposit(id, val);
					 if(val > 0) System.out.println("Deposited $" + val);
				 }
			 }
		 else if(splitCommand.length == 4) {
			 if(splitCommand[0].equals("transfer")) {
				 int origin = Integer.parseInt(splitCommand[1]);
				 double sum = Double.parseDouble(splitCommand[2]);
				 int target = Integer.parseInt(splitCommand[3]);
				 
				 BankAccount originAcc = bas.getBankAccountById(origin);
				 BankAccount targetAcc = bas.getBankAccountById(target);
				 
				 bas.createMoneyTransfer(new MoneyTransfer(originAcc,targetAcc,sum));
				 
				 System.out.println("Transferring $" + sum + " from account" 
				 + origin + " to account " + target);
			 }
		 }
		 
		 
		 
		 //end of dashboard
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
		System.out.println("#######################");
		System.out.println("Enter one of the following commands:");
		System.out.println("To approve all accounts, enter <approve>:");
		System.out.println("To approve a single account, enter <approve> followed by the id of the account:");
		System.out.println("To view a customer's bank accounts, enter <view> followed by the customer's"
				+ " id:");
		System.out.println("To view the transaction log, enter <log>:");
		
		//approval
		
		List<BankAccount> unverifiedAccounts = new ArrayList<BankAccount>();
		
		List<UserAccount> ca = uas.viewAllUserAccounts();
		
		for(UserAccount u : ca) {
			List<BankAccount> ba = bas.viewAllCustomerAccounts(u);
			for(BankAccount b : ba) {
				if(!b.isVerified()) unverifiedAccounts.add(b);
			}
		}
		
		if(unverifiedAccounts.size() > 0) {
			System.out.println("The following accounts must be verified:");
			for(BankAccount b : unverifiedAccounts) {
				System.out.println(b.display());
			}
		}
		
		//user input
		
		String command = manageUserInput();
		
		String[] splitCommand = command.split("\\s+");
		
		
		if(splitCommand.length == 1) {
			 if(splitCommand[0].equals("approve")) {
				 for(BankAccount b : unverifiedAccounts) {
					 b.setVerified(true);
					bas.updateAccount(b);
				}
				 System.out.println("Approved all accounts.");
			 }
			 else if(splitCommand[0].equals("log")) {
				 BufferedReader in;
				try {
					in = new BufferedReader(new FileReader("trace.log"));
					
					while (in.readLine() != null) {
					       System.out.println(in.readLine());          
					 }    
					
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				      
				 
			 }
			 else if(splitCommand[0].equals("logout")) {
				 activeUser = null;
				 beginMenuLoop();
			 }
		 }
		 else if(splitCommand.length == 2) {
			 if(splitCommand[0].equals("view")) {
					int id = Integer.parseInt(splitCommand[1]);
					List<BankAccount> l = bas.viewAllCustomerAccounts(uas.getUserById(id));
					
					for(int i =0;i<l.size();i++) {
						System.out.println(l.get(i).display());
					}
				}
			 else if(splitCommand[0].equals("approve")) {
				 int id = Integer.parseInt(splitCommand[1]);
				 bas.getBankAccountById(id).setVerified(true);
				 //bas.updateAccount();
				 System.out.println("Approved accounts#" + id);
			 }
		 }
		
		
		
		
		
		//see all accounts
		
//		boolean eventsAwaitApproval = false;
//		
//		if(eventsAwaitApproval) {
//			System.out.println("Here is a list of accounts that require your approval:");
//			/*
//			 * Creation of employee accounts requires approval.
//			 * Creation of customer accounts only requires approval if the starting balance > 0.
//			 */
//		}
//		else {
//			System.out.println("No accounts require your approval.");
//		}
		
	}
	
	private String manageUserInput() {
		String input = this.userIn.nextLine();
		return input;
	}

}
