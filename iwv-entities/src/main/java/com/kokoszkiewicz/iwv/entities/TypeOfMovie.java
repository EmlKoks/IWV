package com.kokoszkiewicz.iwv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TypeOfmovie")
public class TypeOfMovie {
	
	@Id
	@Column(name = "id_type")
	private int id;
	
	@Column(name = "type_name")
	private String type_name;
	
	public TypeOfMovie(){
		this.type_name=null;
	}
	
	public TypeOfMovie(String type_name){
		this.type_name=type_name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
} 