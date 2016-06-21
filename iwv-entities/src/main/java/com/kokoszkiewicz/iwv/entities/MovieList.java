package com.kokoszkiewicz.iwv.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "movielist")
public class MovieList{
	
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
		
	@Column(name = "rowNumber")
	private int rowNumber;
	
	@Column(name = "totalRows")
	private int totalRows;
	
	@Column(name = "totalPages")
	private int totalPages;
	
	public MovieList(){
		rowNumber=0;
		totalRows=0;
		totalPages=0;
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
	
	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

} 