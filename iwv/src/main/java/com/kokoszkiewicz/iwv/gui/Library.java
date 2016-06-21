package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokoszkiewicz.iwv.services.FinalVariable;

public class Library extends HttpServlet{
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		int recordOnPage = 10;
		int prodYearFrom = FinalVariable.yearMin;
		int prodYearTo = FinalVariable.yearMax;
		int pageNumber = 1;
		String sort="";
		if(!request.getParameterMap().isEmpty()){
			try{
			recordOnPage = Integer.parseInt(request.getParameter("recordOnPage"));
			} catch (NumberFormatException e){}
			try{
			prodYearFrom = Integer.parseInt(request.getParameter("prodYearFrom"));
			} catch (NumberFormatException e){ }
			try{
			prodYearTo = Integer.parseInt(request.getParameter("prodYearTo"));
			} catch (NumberFormatException e){}
			sort = request.getParameter("sort");	
			try{
				pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e){
				pageNumber=1;
			}
		}
        PageTemplates.library(response, recordOnPage, prodYearFrom, prodYearTo, sort, pageNumber, PageTemplates.userIsLogin(request));
	}
	
	
}
