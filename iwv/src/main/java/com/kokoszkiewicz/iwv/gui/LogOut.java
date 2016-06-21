package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogOut extends HttpServlet{
	final static Logger logger = Logger.getLogger(LogOut.class);
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		HttpSession session = request.getSession();
		logger.info(session.getAttribute("login") + " wylogował się.");
		session.invalidate();
		PageTemplates.message(response, "Zostałeś wylogowany.", "Zostałeś wylogowany.<br/>Zapraszamy ponownie.", "", null);
		
	}

}
