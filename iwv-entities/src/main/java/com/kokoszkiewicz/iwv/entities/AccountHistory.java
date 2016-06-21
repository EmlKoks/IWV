package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounthistory")
public class AccountHistory {
	
	@Id
	@Column(name = "id_transaction")
	private int id;
	
	@Column(name = "id_user")
	private int id_user;

	@Column(name = "id_rent")
	private int id_rent;
	
	@Column(name = "transaction_date")
	private Date transaction_date;

	@Column(name = "balance")
	private float balance;
	
	@Column(name = "difference")
	private float difference;
	
	AccountHistory(){
		this.id=-1;
		this.id_user=-1;
		this.id_rent=-1;
		this.transaction_date=null;
		this.balance=0;	
		this.difference=0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_rent() {
		return id_rent;
	}

	public void setId_rent(int id_rent) {
		this.id_rent = id_rent;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getDifference() {
		return difference;
	}

	public void setDifference(float difference) {
		this.difference = difference;
	}

} 