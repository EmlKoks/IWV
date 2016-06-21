package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class MovieDetails extends HttpServlet{
	final static Logger logger = Logger.getLogger(MovieDetails.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		System.out.println("Atrybut: "+ request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		String login = PageTemplates.userIsLogin(request);
		if(login!=null){
			logger.info("Przeglądanie filmu o id " + id + " przez " + login);
			PageTemplates.movieDetails(response, id, login);
		}
		else PageTemplates.userNotLogged(response);
	}
}