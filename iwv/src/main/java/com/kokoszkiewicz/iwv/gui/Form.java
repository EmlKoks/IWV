package com.kokoszkiewicz.iwv.gui;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="manager")
public class Form {
	String text;
	
	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text=text;
	}
}
