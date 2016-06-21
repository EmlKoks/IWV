package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "id_user")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "register_date")
	private Date register_date;
	
	@Column(name = "last_login")
	private Date last_login;

	@Column(name = "balance")
	private float balance;
	
	@Column(name = "admin")
	private boolean admin;
	
	public User(){
		this.id=0;
		this.email=null;
		this.name=null;
		this.password=null;	
		this.phone=null;
		this.register_date=null;
		this.last_login=null;
		this.balance=0;	
		this.admin=false;
		
	}
	
	public User(String name, String password){
		this.name=name;
		this.password=password;		
	}
	
	public User(String email, String name, String password, Long phone){
		this.email=email;
		this.name=name;
		this.password=password;	
		this.phone=phone;
		this.register_date= new Date();
	}
	
	public User(String email, String name, String password, Long phone, Date register_date, Date last_login, float balance){
		this.email=email;
		this.name=name;
		this.password=password;	
		this.phone=phone;
		this.register_date=register_date;
		this.last_login=last_login;
		this.balance=balance;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void addToBalance(float balance){
		this.balance+=balance;		
	}
	
	public boolean getAdmin(){
		return admin;
	}
	
	public void setAdmin(boolean admin){
		this.admin=admin;
	}

} 