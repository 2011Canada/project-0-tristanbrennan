package com.revature.models;

public class MoneyTransfer implements Displayable {
	
	private int id;
	BankAccount origin, target;
	double sum;
	
	public MoneyTransfer(int id, BankAccount origin, BankAccount target, double sum) {
		super();
		this.id = id;
		this.origin = origin;
		this.target = target;
		this.sum = sum;
	}
	
	public MoneyTransfer(BankAccount origin, BankAccount target, double sum) {
		this.id = -1;
		this.origin = origin;
		this.target = target;
		this.sum = sum;
	}
	
	public double resolveMoneyTransfer() {
		double amt = origin.withdraw(sum);
		target.deposit(amt);
		sum = sum - amt;
		return sum;
	}

	public BankAccount getOrigin() {
		return origin;
	}

	public void setOrigin(BankAccount origin) {
		this.origin = origin;
	}

	public BankAccount getTarget() {
		return target;
	}

	public void setTarget(BankAccount target) {
		this.target = target;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public String display() {
		String s = "ID#" + id + " Origin: " + origin.getAccountId() + " Sum: $" + sum;
		return s;
	}

	public int getId() {
		return id;
	}

}
