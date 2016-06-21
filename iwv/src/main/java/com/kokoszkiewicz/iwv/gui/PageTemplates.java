package com.kokoszkiewicz.iwv.gui;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kokoszkiewicz.iwv.entities.Movie;
import com.kokoszkiewicz.iwv.entities.User;
import com.kokoszkiewicz.iwv.services.Brain;
import com.kokoszkiewicz.iwv.services.FinalVariable;

public class PageTemplates {
	
	public static String createHeader(String title, String login){
		StringBuilder header = new StringBuilder();
		header.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +")
				.append("http://www.w3.org/TR/html4/loose.dtd\">\n")
				.append("<html> \n")
				.append("<head> \n")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8 \">\n")
				.append("<title>").append(title).append("</title> \n")
				.append("<link media=\"all\" rel=\"stylesheet\" type=\"text/css\" href=\"./res/style.css\"/>")
				.append("</head> \n")
				.append("<body>\n")
				.append("<div id=\"page\">\n")
				.append("<table id=\"header\"><tr>\n")
				.append("<td style=\"width=60%\"><div id=\"logo\"><a href=\".\">Internetowa Wypożyczalnia Video<a/></div></td>\n")
				.append("<td><div id=\"user_menu\">\n")
				.append(createTabUserMenu(login))
				.append("</div></td>\n")
				.append("</tr></table>\n")
				.append("<div id=menu>\n")
				.append("<ul><li><a href=\"./Library\">Biblioteka</a></li>\n")
				.append("<li><a href=\".\">Strona główna</a></li>\n")
				.append("<li><a href=\".\">Strona główna</a></li>\n")
				.append(Brain.isAdmin(login)?"<li><a href=\"./AdminPanel\">Panel administracyjny</a></li>\n":"<li><a href=\".\">Strona główna</a></li>\n")
				.append("</ul></div>\n")
				.append("<div id=\"content\">\n");
		
		return header.toString();
	}
	
	private static String createTabUserMenu(String login){
		if(login==null) return new StringBuilder("<div id=\"user_menu_not_logged\">")
				.append("<a href=\"./Login\">Logowanie</a><br/>\n")
				.append("<a href=\"./Register\">Rejestracja</a></div>\n")
				.toString();
		else return new StringBuilder("<div id=\"user_menu_logged\">")
				.append("Witaj, "+ login +"<br/>\n")
				.append("<a href=\"./Profile\">Mój profil</a><br/>\n")
    			.append("<a href=\"./RentedMovies\">Moje filmy</a><br/>\n")
				.append("<a href=\"./LogOut\">Wyloguj</a><br/>")
				.append("</div>")
			.toString();
	}
	
	private static String createBottom(){
		StringBuilder header = new StringBuilder();
		header.append("</div><div id=\"bottom\">")
				.append("Created by KOK$")
				.append("</div></div>\n")
				.append("</body> \n")
				.append("</html>");
		return header.toString();
	}
	
	public static void loginCorrect(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Logowanie Poprawne!", login))
        			.append("<h1>Logowanie poprawne!</h1>\n")
        			.append("<h2>Witaj ").append(login).append("</h2>\n")
        			.append(createBottom()).toString()
                );      
        }		
	
	public static void message(HttpServletResponse response, String title, String message, String backPage, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader(title, login))
    			.append("<h1>"+message+"</h1>\n")
    			.append("<a href=\"."+backPage+"\">Wróć</a><br/>")
    			.append(createBottom()).toString()
            );  
        }
	
	public static void profile(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Profil użytkownika", login))
        		.append("<h1>Profil</h1>\n")
    			.append("<h2>Witaj ").append(login).append("</h2>\n")
    			.append(Brain.getUserDetails(login))
    			.append(createBottom()).toString()
            );    
        }
	
	public static void userLogged(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Strona Główna", login))
    			.append("<h1>Menu!</h1>\n")
    			.append("<a href=\"./Profile\">Mój profil</a><br/>\n")
    			.append("<a href=\"./RentedMovies\">Wypożyczone filmy</a><br/>\n")
    			.append("<a href=\"./Library\">Przeglądaj filmy</a><br/>\n")
    			.append("<a href=\"./LogOut\">Wyloguj</a><br/>")
    			.append(createBottom()).toString()
            );
        }
	
	public static void userNotLogged(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Strona Główna", null))
    			.append("<h1>Menu!</h1>\n")
    			.append("<a href=\"./Login\">Logowanie</a><br/>\n")
    			.append("<a href=\"./Register\">Rejestracja</a><br/>")
    			.append(createBottom()).toString()
            );    
        }
	
	public static void login(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Internetowa Wypożyczalnia Video - Logowanie", null))
    			.append("<h1>Logowanie!</h1>\n")
    			.append("<form action=\"Login\" method=\"post\">")
    			.append("Login:  <input type=\"text\" name=\"username\" size=\"20px\"> <br>")
                .append("Hasło:  <input type=\"password\" name=\"password\" size=\"20px\"> <br><br>")
                .append("<input type=\"submit\" value=\"Loguj\">")
                .append("</form>")
    			.append(createBottom()).toString()
            );    
        }
	
	public static void register(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Internetowa Wypożyczalnia Video - Rejestracja", null))
    			.append("<h1>Rejestracja</h1>\n")
    			.append("<form action=\"Register\" method=\"post\">")
    			.append("<table>")
    			.append("<tr><td>E-Mail:</td><td><input type=\"text\" name=\"email\" size=\"20px\"></td></tr>")
    			.append("<tr><td>Login:</td><td><input type=\"text\" name=\"username\" size=\"20px\"></td></tr>")
    			.append("<tr><td>Hasło:</td><td><input type=\"password\" name=\"password\" size=\"20px\"></td></tr>")
    			.append("<tr><td>Ponownie Hasło:</td><td><input type=\"password\" name=\"password2\" size=\"20px\"></td></tr>")
    			.append("<tr><td>Telefon:</td><td><input type=\"text\" name=\"phone\" size=\"20px\"></td></tr>")
    			.append("</table>")
    			.append("<input type=\"submit\" value=\"Rejestruj\">")
    			.append("</form>")
    			.append(createBottom()).toString()
            );    
        }
	
	public static void library(HttpServletResponse response, int recordOnPage, int prodYearFrom, int prodYearTo, String sort, int pageNumber, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Biblioteka filmów", login))
    			.append("<h1>Biblioteka filmów</h1>\n")
    			.append("<form action=\"Library\" method=\"GET\" id=\"filters\"><table>")
    			.append("<tr><td>Liczba wyników na stronie"+numberRecordsOnPage(recordOnPage)+"</td></tr>")
    			.append("<tr><td>Rok produkcji"+productionYear(prodYearFrom, prodYearTo)+"</td></tr>")
    			.append("<tr><td>Sortuj według"+sort()+"</td></tr>")
    			.append("<tr><td><input type=\"submit\" value=\"Szukaj\"></td></tr>\n")
    			.append("</table></form>")
    			.append(Brain.showLibrary(recordOnPage, prodYearFrom, prodYearTo, sort, pageNumber))
    			.append(createBottom()).toString()
            );
        }
	
	public static void usersList(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Lista użytkowników", login))
    			.append("<h1>Lista użytkowników</h1>\n")
    			.append(Brain.showUsersList())
    			.append(createBottom()).toString()
            );
        }
	
	public static void watchMovie(HttpServletResponse response, String login, int id) throws IOException{
        PrintWriter out = response.getWriter();
        Movie movie = Brain.getMovie(id);
        out.println (new StringBuilder(createHeader(movie.getTitle(), login))
    			.append("<h1>"+movie.getTitle()+"</h1>\n")
    			.append("<iframe width=\"854\" height=\"480\" src=\""+ movie.getTrailer_link()+"\" frameborder=\"0\" allowfullscreen></iframe>")
    			.append(createBottom()).toString()
            );
        }

	public static void newMovie(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Nowy film", login))
    			.append("<h1>Nowy film</h1>\n")
				.append("<form action=\"AddNewMovie\" method=\"post\">\n")
				.append("<table>\n")
				.append("<tr><td>Tytuł filmu:</td><td><input type=\"text\" name=\"title\" size=\"50px\"></td></tr>\n")
				.append("<tr><td>Reżyser:</td><td><input type=\"text\" name=\"director\" size=\"50px\"></td></tr>\n")
				.append("<tr><td>Data premiery:</td><td><input type=\"text\" name=\"premiere_date_day\" value=\"DD\" size=\"2px\">-")
				.append("<input type=\"text\" name=\"premiere_date_month\" value=\"MM\" size=\"2px\">-")
				.append("<input type=\"text\" name=\"premiere_date_year\" value=\"YYYY\" size=\"5px\"></td></tr>\n")
				.append(Brain.typeOfMovie())
				.append("<tr><td>Link do trailera:</td><td><input type=\"url\" name=\"trailer_link\" size=\"50px\"></td></tr>\n")
				.append("<tr><td>Link do plakatu:</td><td><input type=\"url\" name=\"poster_link\" size=\"50px\"></td></tr>\n")
				.append("<tr><td>Cena:</td><td><input type=\"text\" name=\"price\" size=\"20px\"></td></tr>\n")
				.append("</table>\n")
				.append("<input type=\"submit\" value=\"Dodaj nowy film\">\n")
				.append("</form>\n")
    			.append(createBottom()).toString()
            );    
      }
	
	public static void chargeAccount(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Ładowanie konta", login))
    			.append("<h1>Ładowanie konta</h1>\n")
				.append("<form action=\"ChargeAccount\" method=\"post\">\n")
				.append("<table>\n")
				.append("<tr><td>Kwota:</td><td><input type=\"text\" name=\"amount\" size=\"20px\"></td></tr>\n")
				.append("</table>\n")
				.append("<input type=\"submit\" value=\"Doładuj\">\n")
				.append("</form>\n")
    			.append(createBottom()).toString()
            );
        }

	
	public static void movieDetails(HttpServletResponse response, int id, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Film", login))
    			.append(Brain.getMovieDetails(id))
    			.append("<form action=\"./RentMovie\" method=\"GET\"><input type=\"hidden\" name=\"id\" value=\""+id+"\"><input type=\"submit\" value=\"Wypożycz ten film\"></form><br/>")
    			.append("<a href=\"./Library\">Wróć</a><br/>")
    			.append(createBottom()).toString()
            );    
      }	
	
	public static void rentedMovies(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Wypożyczone Filmy", login))
        		.append("<h1>Wypożyczone filmy</h1>\n")
    			.append(Brain.getRentedMovies(login))
    			.append(createBottom()).toString()
            );    
      }	
	
	
	public static void rentMovie(HttpServletResponse response, int id, String login) throws IOException{
        PrintWriter out = response.getWriter();
        Movie movie = Brain.getMovie(id);
        User user = Brain.getUser(login);
        if(movie.getPrice()>user.getBalance()){
        	out.println (new StringBuilder(createHeader("Wypożycz Film", login))
            		.append("Masz za mało środków na koncie!<br/>")
            		.append("Cena tego filmu to: " + movie.getPrice() + "zł<br/>")
            		.append("Stan Twojego konta to: " + user.getBalance() + "zł<br/>")
            		.append("<form action=\"./ChargeAccount\" method=\"GET\"><input type=\"submit\" value=\"Doładuj konto\"></form><br/>")
        			.append(createBottom()).toString()
                );    	
        }
        else out.println (new StringBuilder(createHeader("Wypożycz Film", login))
        		.append("Cena tego filmu to: " + movie.getPrice() + "zł<br/>")
        		.append("Stan Twojego konta to: " + user.getBalance() + "zł<br/>")
        		.append("Czy chcesz wypożyczyć film " + movie.getTitle() + "?<br/>") 
        		.append("<form action=\"RentMovie\" method=\"post\">")
        		.append("<input type=\"hidden\" name=\"id\" value=\""+id+"\">")
        		.append("<input type=\"submit\" value=\"Wypożycz\">\n")
        		.append("</form>")
    			.append(createBottom()).toString()
            );  
      }
	
	public static void adminPanel(HttpServletResponse response, String login) throws IOException{
        PrintWriter out = response.getWriter();
        out.println (new StringBuilder(createHeader("Panel Administracyjny", login))
        		.append("<h1>Panel Administracyjny</h1>\n")
    			.append("<a href=\"./AddNewMovie\">Dodaj nowy film</a><br/>\n")
    			.append("<a href=\"./UsersList\">Lista użytkowników</a><br/>\n")
    			.append(createBottom()).toString()
            );    
      }
	
	public static String numberRecordsOnPage(int num){
		StringBuilder str = new StringBuilder();
		str.append("<select name=\"recordOnPage\" form=\"filters\">")
		.append("<option value=\"10\""+ (num==10?"selected":"") +">10</option>")
		.append("<option value=\"20\""+ (num==20?"selected":"") +">20</option>")
		.append("<option value=\"50\""+ (num==50?"selected":"") +">50</option>")
		.append("<option value=\"100\""+ (num==100?"selected":"") +">100</option>")
		.append("</select>");
		return str.toString();
	}
		
	public static String productionYear(int prodYearFrom, int prodYearTo){
		StringBuilder str = new StringBuilder();
		int yearMin=FinalVariable.yearMin;
		int yearMax=FinalVariable.yearMax;
		str.append("<select name=\"prodYearFrom\" form=\"filters\">");
		for(int i=yearMin ; i<=yearMax ; ++i) str.append("<option value=\""+i+"\""+(i==prodYearFrom?"selected":"") +">"+i+"</option>");
		str.append("</select>-");
		str.append("<select name=\"prodYearTo\" form=\"filters\">");
		for(int i=yearMin ; i<=yearMax ; ++i) str.append("<option value=\""+i+"\""+(i==prodYearTo?"selected":"") +">"+i+"</option>");
		str.append("</select>");
		return str.toString();
	}
	public static String sort(){
		StringBuilder str = new StringBuilder();
		str.append("<select name=\"sort\" form=\"filters\">")
		.append("<option value=\"title\">tytuł</option>")
		.append("<option value=\"director\">reżyser</option>")
		.append("<option value=\"year\">rok produkcji rosnąco</option>")
		.append("<option value=\"yearDESC\">rok produkcji malejąco</option>")
		.append("</select>");
		return str.toString();
	}
	
	public static String userIsLogin(HttpServletRequest request){		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		System.out.println("Login sesji: " + login);
	    if(login!=null && Brain.isUserCorrect(login)) return login;
	    else return null;
	}
	

	
}
