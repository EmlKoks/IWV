package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.services.Brain;

public class AddNewMovie extends HttpServlet{
	final static Logger logger = Logger.getLogger(AddNewMovie.class);
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		String login = PageTemplates.userIsLogin(request);
		if(login!=null){
			logger.info("Dodawanie filmu przez " + login);
			PageTemplates.newMovie(response, login);
		}
		else PageTemplates.userNotLogged(response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		String login = PageTemplates.userIsLogin(request);
		if(login==null){
			PageTemplates.userNotLogged(response);
			return;
		}
		System.out.println("Dodawanie nowego filmu!");
		String title = request.getParameter("title");
        String director = request.getParameter("director");
        GregorianCalendar date = new GregorianCalendar(Integer.parseInt(request.getParameter("premiere_date_year")), Integer.parseInt(request.getParameter("premiere_date_month")), Integer.parseInt(request.getParameter("premiere_date_day")));
        Date premiere_date = date.getTime();
        String trailer_link = request.getParameter("trailer_link");
        String poster_link = request.getParameter("poster_link");
        Float price = Float.parseFloat(request.getParameter("price"));
        String[] type_name = request.getParameterValues("type_name");
        if( Brain.addMovie(title, director, premiere_date, trailer_link, poster_link, price, type_name)){
        	logger.info(login + "dodał nowy film.");
        	PageTemplates.message(response, "Dodałeś nowy film!", "Dodałeś nowy film!", "", login);
        }
        else{
        	logger.info("Błędna próba dodania filmu przez " + login);
        	PageTemplates.message(response, "Dodawanie filmu nie powiodło się!", "Błędne dane", "/AddNewFilm", login);
        }
		
	}

}
