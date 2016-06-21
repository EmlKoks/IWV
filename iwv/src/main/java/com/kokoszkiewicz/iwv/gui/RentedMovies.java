package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RentedMovies extends HttpServlet {

	final static Logger logger = Logger.getLogger(RentedMovies.class);
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		String login = PageTemplates.userIsLogin(request);
		if(login!=null){
			logger.info("Przeglądanie wypożyczonych filmów przez " + login);
			PageTemplates.rentedMovies(response, login);
		}
		else PageTemplates.message(response, "Nie jesteś zalogowany.", "Nie jesteś zalogowany.", "", login);
	}
}

