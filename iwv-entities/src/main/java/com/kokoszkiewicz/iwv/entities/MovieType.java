package com.kokoszkiewicz.iwv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MovieType")
public class MovieType {
	
	@Id
	@Column(name = "id_movietype")
	private int id_movietype;
	
	@Column(name = "id_movie")
	private int id_movie;
	
	@Column(name = "id_type")
	private int id_type;
	
	public MovieType(){
		this.id_movie=-1;
		this.id_type=-1;
	}
	
	public MovieType(int id_movie, int id_type){
		this.id_movie=id_movie;
		this.id_type=id_type;
	}
	
	public int getId_movietype() {
		return id_movietype;
	}

	public void setId_movietype(int id_movietype) {
		this.id_movietype = id_movietype;
	}
	
	public int getId_movie() {
		return id_movie;
	}

	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}
} 