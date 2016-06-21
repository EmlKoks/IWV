package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@Column(name = "id_movie")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "premiere_date")
	private Date premiere_date;

	@Column(name = "director")
	private String director;
	
	@Column(name = "trailer_link")
	private String trailer_link;
	
	@Column(name = "poster_link")
	private String poster_link;

	@Column(name = "price")
	private float price;
	
	public Movie(){
		this.title=null;
		this.premiere_date=null;
		this.director=null;
		this.trailer_link=null;
		this.poster_link=null;
		this.price=-1;			
		
	}
	
	public Movie(String title, Date premiere_date, String director, String trailer_link, String poster_link, float price){
		this.title=title;
		this.premiere_date=premiere_date;
		this.director=director;
		this.trailer_link=trailer_link;
		this.poster_link=poster_link;
		this.price=price;		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getPremiere_date() {
		return premiere_date;
	}

	public void setPremiere_date(Date premiere_date) {
		this.premiere_date = premiere_date;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getTrailer_link() {
		return trailer_link;
	}

	public void setTrailer_link(String trailer_link) {
		this.trailer_link = trailer_link;
	}
	
	public String getPoster_link() {
		return poster_link;
	}

	public void setPoster_link(String poster_link) {
		this.poster_link = poster_link;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

} 