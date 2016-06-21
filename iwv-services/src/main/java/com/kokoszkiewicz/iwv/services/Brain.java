package com.kokoszkiewicz.iwv.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kokoszkiewicz.iwv.dao.MovieDAO;
import com.kokoszkiewicz.iwv.dao.MovieTypeDAO;
import com.kokoszkiewicz.iwv.dao.RentedMovieDAO;
import com.kokoszkiewicz.iwv.dao.TypeOfMovieDAO;
import com.kokoszkiewicz.iwv.dao.UserDAO;
import com.kokoszkiewicz.iwv.entities.Movie;
import com.kokoszkiewicz.iwv.entities.MovieList;
import com.kokoszkiewicz.iwv.entities.RentedMovie;
import com.kokoszkiewicz.iwv.entities.RentedMovies;
import com.kokoszkiewicz.iwv.entities.TypeOfMovie;
import com.kokoszkiewicz.iwv.entities.User;

public class Brain{
	final static Logger logger = Logger.getLogger(Brain.class);
	
	public static boolean login(String login, String password){
		logger.info("Logowanie użytkownika " + login);
		User user = new User(login, password);
		if(UserDAO.isValidLogin(user)) return true;
		else return false;
	}
	
	public static boolean register(String email, String name, String password, String phone){
		logger.info("Rejestracja użytkownika " + name);
		Long phoneNumber;
		try{
			phoneNumber= phone==""?null:Long.parseLong(phone);
		}
		catch (NumberFormatException ex){
			return false;
		}
		User user = new User(email, name, password, phoneNumber);
		System.out.println("Rejestracja!");
		if(UserDAO.register(user)) return true;
		else return false;
	}
	
	public static boolean addMovie(String title, String director, Date premiere_date, String trailer_link, String poster_link, Float price, String[] type_name){
		logger.info("Dodawanie filmu " + title);
		Movie  movie = new Movie(title, premiere_date, director, trailer_link, poster_link, price);
		System.out.println("Dodawanie filmu!");
		if(MovieDAO.addMovie(movie, type_name) && MovieTypeDAO.addMovieTypes(movie, type_name)) return true;
		else return false;
	}
	
	
	public static String showLibrary(int recordOnPage, int prodYearFrom, int prodYearTo, String sort, int pageNumber){
		StringBuilder movies = new StringBuilder();
		movies.append("<table id=\"tab_library\"><tr><td></td><td>Tytuł</td><td>Reżyser</td><td>Rok produkcji</td><td></td></tr>\n");
		int totalPages=0;
		for(MovieList f:MovieDAO.getMovies(recordOnPage, prodYearFrom, prodYearTo, sort, pageNumber)){
			totalPages=f.getTotalPages();
			movies.append("<tr><td><img src=\"" + (f.getPoster_link()!=null?f.getPoster_link():FinalVariable.defPoster) +"\" alt=\"Poster\" style=\"width:100px;height:100px;\"></td>\n")
				.append("<td>"+f.getTitle()+"</td>\n")
				.append("<td>"+f.getDirector()+"</td>\n")
				.append("<td>");
				Calendar cal= Calendar.getInstance();
				cal.setTime(f.getPremiere_date());
				movies.append(cal.get(Calendar.YEAR)+"</td>\n")
				.append("<td><form action=\"./Movie\" method=\"GET\">")
				.append("<input type=\"hidden\" name=\"id\" value=\""+f.getId()+"\">")
				.append("<input type=\"submit\" value=\"Przedlądaj\"></form></td></tr>\n");
		}
		movies.append("</table>");
		StringBuilder urlParam = new StringBuilder("./Library?");
		if(recordOnPage!=-1) urlParam.append("recordOnPage="+recordOnPage+"&");
		if(prodYearFrom!=-1) urlParam.append("prodYearFrom="+prodYearFrom+"&");
		if(prodYearTo!=-1) urlParam.append("prodYearTo="+prodYearTo+"&");
		if(!"".equals(sort)) urlParam.append("sort="+sort+"&");
		movies.append("<center><a href=\""+urlParam+"&pageNumber="+1+"\">Pierwsza strona</a> ");
		if(pageNumber>2)movies.append("<a href=\""+urlParam+"&pageNumber="+1+"\">Poprzednia strona</a> ");
		if(pageNumber<totalPages-1)movies.append("<a href=\""+urlParam+"&pageNumber="+(pageNumber+1)+"\">Następna strona</a> ");
		movies.append("<a href=\""+urlParam+"&pageNumber="+totalPages+"\">Ostatnia strona</a></center> ");
		return movies.toString();
		
	}
	
	public static String typeOfMovie(){
		StringBuilder type = new StringBuilder();
		type.append("<tr><td>Gatunek filmu:</td>");
		int a=1;
		for(TypeOfMovie i: TypeOfMovieDAO.getTypeOfMovie()){
			type.append("<td><input type=\"checkbox\" name=\"type_name\" value=\""+ i.getType_name() + "\">"+i.getType_name()+"</td>");
			if(a==3){
				type.append("</tr>\n<tr><td/>");
				a=1;
			}
			else ++a;
		}
		if(a!=1)type.append("</tr>\n");
		return type.toString();
	} 
	
    public static String getMovieDetails(int id){
    	logger.info("Pobieranie szczegółów filmu o id " + id);
    	Movie movie = MovieDAO.getMovieDetails(id);
    	StringBuilder details = new StringBuilder();
    	details.append("<table><tr><td><table>")
    			.append("<tr><td>Tytuł</td><td>"+movie.getTitle()+"</td></tr>")
    			.append("<tr><td>Reżyser</td><td>"+movie.getDirector()+"</td></tr>")
    			.append("<tr><td>Data produkcji</td><td>"+movie.getPremiere_date().toString()+"</td></tr>")
    			.append("<tr><td>Cena</td><td>"+movie.getPrice()+"</td></tr>")
    			.append("</table></td><td>")
    			.append("<img src=\"" + (movie.getPoster_link()!=null?movie.getPoster_link():FinalVariable.defPoster) +"\" alt=\"Poster\">")
    			.append("</td></tr></table>");
    	return details.toString();
    }
    
    public static boolean isUserCorrect(String login){
    	logger.info("Weryfikacja użytkownika " + login);
    	return UserDAO.isExistUser(login);
    }
    
    public static boolean isAdmin(String login){
    	return login==null?false:UserDAO.getUser(login).getAdmin();
    }
    
    public static String showUsersList(){
    	StringBuilder userListStr = new StringBuilder();
    	userListStr.append("<table id=\"tab_userlist\"><tr><td>Login</td><td>E-Mail</td><td>Nr telefonu</td><td>Data rejestracji</td><td>Data ostatniego logowania</td></tr>\n");
    	for(User user: UserDAO.getUsersList()) 
    		userListStr.append("<tr ")
    				.append(user.getAdmin()==true?"id=\"user_is_admin\"><td>":"><td>")
					.append(user.getName())
    				.append("</td><td>"+user.getEmail())
    				.append("</td><td>")
    				.append(user.getPhone()==null?"":user.getPhone())
    				.append("</td><td>"+user.getRegister_date())
    				.append("</td><td>")
    				.append(user.getLast_login()==null?"":user.getLast_login())
    				.append("</td></tr>\n");
    	userListStr.append("</table>");
    	System.out.println("1324asdasdasd"+userListStr.toString());
    	return userListStr.toString();
    }
    
    public static String getUserDetails(String login){
    	logger.info("Pobieranie szczegółów użytkownika " + login);
    	User user=UserDAO.getUser(login);
    	if(user == null) return "";//jakiś wyjątek
    	StringBuilder details = new StringBuilder();
    	details.append("<table>")
			.append("<tr><td>Nick</td><td>"+user.getName()+"</td></tr>")
			.append("<tr><td>E-Mail</td><td>"+user.getEmail()+"</td></tr>")
			.append("<tr><td>Numer telefonu</td><td>"+user.getPhone()+"</td></tr>")
			.append("<tr><td>Data rejestracji</td><td>"+user.getRegister_date().toString()+"</td></tr>")
			.append("<tr><td>Stan konta</td><td>"+user.getBalance()+"</td><td><form action=\"./ChargeAccount\" method=\"GET\"><input type=\"submit\" value=\"Doładuj konto\"></form><br/></td></tr>")
			.append("</table>");
    	return details.toString();
    }
    
    public static boolean rentMovie(int id, String login){
    	logger.info("Wypożyczanie filmu o id " + id + " przez " + login);
    	Movie movie = MovieDAO.getMovieDetails(id);
    	User user = UserDAO.getUser(login);
    	UserDAO.chargeAccount(movie.getPrice()*(-1), login);
    	Date rentTime = new Date();
    	Date endTime = new Date(rentTime.getTime()+ FinalVariable.dayInMilis*30);
    	RentedMovie rentedMovie = new RentedMovie(movie.getId(), user.getId(), rentTime , endTime);
    	if(RentedMovieDAO.rentMovie(rentedMovie)) return true;
    	else{
    		UserDAO.chargeAccount(movie.getPrice(), login);
    		return false;
    	}
    }
    
    public static boolean chargeAccount(Float amount, String login) throws IOException{
    	logger.info("Zasilanie konta użytkownika " + login);
    	return UserDAO.chargeAccount(amount, login);
    
    }
    
    public static boolean isRentedMovie(String login, int id){
    	return RentedMovieDAO.isRentedMovie(UserDAO.getUser(login).getId(), id);
    }
    
    public static Movie getMovie(int id){
    	return MovieDAO.getMovieDetails(id);    	
    }
    public static User getUser(String login){
    	return UserDAO.getUser(login);
    }
    
    public static String getRentedMovies(String login){
    	StringBuilder rentedBuilder = new StringBuilder();
    	rentedBuilder.append("<table>")
    		.append("<tr><td></td><td>Tytuł</td><td>Termin ważności</td><td></td></tr>");
    	for(RentedMovies i : RentedMovieDAO.getRentedMovies(login)){
    		rentedBuilder.append("<tr><td><img src=\""+(i.getPoster_link()!=null?i.getPoster_link():FinalVariable.defPoster)+"\" alt=\"Poster\" style=\"width:100px;height:100px;\"></td>")
    					.append("<td>"+i.getTitle()+"</td>")
    					.append("<td>"+new SimpleDateFormat("dd-MM-yyyy HH:mm").format(i.getRent_end_date())+"</td>")
    					.append("<td><form action=\"./WatchMovie\" method=\"GET\">")
    					.append("<input type=\"hidden\" name=\"id\" value=\""+i.getId_movie()+"\">")
    					.append("<input type=\"submit\" value=\"Oglądaj\"></form></td></tr>\n");
    	}
    	rentedBuilder.append("</table>");
    	return rentedBuilder.toString();
    }
    
}
