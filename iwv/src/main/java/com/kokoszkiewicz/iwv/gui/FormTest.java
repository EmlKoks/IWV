package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormTest extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<html xmlns=\"http://www.w3.org/1999/xhtml\"" +
	"xmlns:h=\"http://java.sun.com/jsf/html\""+ 
	"xmlns:f=\"http://java.sun.com/jsf/core\""+ 
	"xmlns:p=\"http://primefaces.org/ui\">"+ 
	"<h:head>"+  
  	"</h:head>"+
	"<h:body>"+  
		"<p:inputText id=\"value\" value=\"#{manager.value}\" />"+
	"</h:body>"+ 
"</html> ");
	}

}
