package com.app.aforo255.account.model.entity;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = -6165096774869245468L;
	
	private Integer id;
	
	private double amount;
	
	private String type;
	
	private String creationDate;
	
	private int accountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	

}
