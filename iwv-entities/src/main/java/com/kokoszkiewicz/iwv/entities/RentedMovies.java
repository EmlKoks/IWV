package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RentedMovies")
public class RentedMovies {
	
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
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "poster_link")
	private String poster_link;
	
	public RentedMovies(){
		this.rent_date = null;
		this.rent_end_date = null;
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPoster_link() {
		return poster_link;
	}

	public void setPoster_link(String poster_link) {
		this.poster_link = poster_link;
	}
} 