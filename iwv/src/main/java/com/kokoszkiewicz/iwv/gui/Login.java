package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.services.Brain;

public class Login extends HttpServlet{
	final static Logger logger = Logger.getLogger(Login.class);
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username=="" || password==""){
        	logger.info("Próba logowania.");
        	PageTemplates.message(response, "Logowanie nie powiodło się!", "Błędny login lub hasło", "/Login", null);
        	return;        	
        }
        if( Brain.login(username, password)){
        	HttpSession session = request.getSession();
        	session.setAttribute("login", username);
        	session.setMaxInactiveInterval(60*60);
        	logger.info(username + " zalogował się.");
        	PageTemplates.loginCorrect(response, username);
        }
        else{
        	logger.info("Próba logowania użytkownika o loginie " + username);
        	PageTemplates.message(response, "Logowanie nie powiodło się!", "Błędny login lub hasło", "/Login", null);
        }
    } 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		PageTemplates.login(response);
	}
}
