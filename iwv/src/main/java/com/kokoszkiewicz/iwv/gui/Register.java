package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.services.Brain;

public class Register extends HttpServlet {

	final static Logger logger = Logger.getLogger(Register.class);
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		System.out.println("Rejestracja!");
		String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");
        if(username==""){
        	PageTemplates.message(response, "Rejestracja nie powiodła się!", "Brak loginu", "/Register", null);
        	return;
        }
        if(email==""){
        	PageTemplates.message(response, "Rejestracja nie powiodła się!", "Brak emailu", "/Register", null);
        	return;
        }
        if(password2=="" || !(password2.equals(password))){
        	PageTemplates.message(response, "Rejestracja nie powiodła się!", "Hasła nie są takie same", "/Register", null);
        	return;
        }
        if( Brain.register(email, username, password, phone)){
        	logger.info("Poprawna rejestracja użytkownika " + username);
        	PageTemplates.message(response, "Rejestracja poprawna!", "Rejestracja poprawna!", "/Login", null);
        }
        else{
        	logger.info("Problem z rejestracją użytkownika " + username);
        	PageTemplates.message(response, "Rejestracja nie powiodła się!", "Błędne dane", "/Register", null);
        }
	} 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		PageTemplates.register(response);
	}
}

