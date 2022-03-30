package com.revature.bank.model;

public class Account {
	private int a_id;
	private int c_id;
	private String number;
	private int balance;
	
	
	public Account() {
		super();
	}


	public Account(int a_id, int c_id, String number, int balance) {
		super();
		this.a_id = a_id;
		this.c_id = c_id;
		this.number = number;
		this.balance = balance;
	}


	

	public int getA_id() {
		return a_id;
	}


	public void setA_id(int a_id) {
		this.a_id = a_id;
	}


	public int getC_id() {
		return c_id;
	}


	public void setC_id(int c_id) {
		this.c_id = c_id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Account [a_id=" + a_id + ", c_id=" + c_id + ", number=" + number + ", balance=" + balance
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	

}
