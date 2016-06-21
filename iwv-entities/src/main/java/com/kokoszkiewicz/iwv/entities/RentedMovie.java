package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RentedMovie")
public class RentedMovie {
	
	@Id
	@Column(name = "id_rent")
	private int id;
	
	@Column(name = "id_movie")
	private int id_movie;
	
	@Column(name = "id_user")
	private int id_user;
	
	@Column(name = "rent_date")
	private Date rent_date;
	
	@Column(name = "rent_end_date")
	private Date rent_end_date;
	
	public RentedMovie(){
		this.id_movie=-1;
		this.id_user=-1;
		this.rent_date = null;
		this.rent_end_date = null;
	}
	
	public RentedMovie(int id_movie, int id_user, Date rent_date, Date rent_end_date){
		this.id_movie=id_movie;
		this.id_user=id_user;
		this.rent_date = rent_date;
		this.rent_end_date = rent_end_date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId_movie() {
		return id_movie;
	}

	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public Date getRent_date() {
		return rent_date;
	}

	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}
	
	public Date getRent_end_date() {
		return rent_end_date;
	}

	public void setRent_end_date(Date rent_end_date) {
		this.rent_end_date = rent_end_date;
	}
} 