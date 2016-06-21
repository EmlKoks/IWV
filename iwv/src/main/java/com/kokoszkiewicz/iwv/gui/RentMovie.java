package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.services.Brain;

public class RentMovie extends HttpServlet {
	final static Logger logger = Logger.getLogger(RentMovie.class);
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		int id=-1;
		String login="";
		try{
			id=Integer.parseInt(request.getParameter("id"));
			login = PageTemplates.userIsLogin(request);
			if(login==null)  throw new Exception();
			if(Brain.rentMovie(id, login)){
				logger.info(login + " wypożyczył filmu o id " + id);
				PageTemplates.message(response, "Wypożyczanie filmu powiodło się!", "Wypożyczanie filmu powiodło się!", "/Library", login);
			}
			else throw new Exception();
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
			logger.info("Problem z wypożyczeniem filmu o id " + id + " przez " + login);
			PageTemplates.message(response, "Coś poszło nie tak", "Wypożyczanie nie powiodło się!", "/Library", null);
		}
	} 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		try{
			int id=Integer.parseInt(request.getParameter("id"));
			String login = PageTemplates.userIsLogin(request);
			if(login==null)  throw new Exception();
			PageTemplates.rentMovie(response, id, login);
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
			response.sendRedirect("./Library");
		}
	}
}

