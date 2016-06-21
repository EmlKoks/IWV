package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.services.Brain;

public class WatchMovie extends HttpServlet{

	final static Logger logger = Logger.getLogger(WatchMovie.class);
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		String login = PageTemplates.userIsLogin(request);
		try{
			int id=Integer.parseInt(request.getParameter("id"));
			if(Brain.isRentedMovie(login, id)){
				logger.info(login + " ogląda film o id " + id);
				PageTemplates.watchMovie(response, login, id);
			}
			else PageTemplates.message(response, "Nie możesz oglądać tego filmu.", "Nie możesz oglądać tego filmu.", "", login);
		}catch(NumberFormatException ex){
			PageTemplates.message(response, "Błędny film.", "Błędny film.", "", login);
		}

	}
		
}
