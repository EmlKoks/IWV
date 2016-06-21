package com.kokoszkiewicz.iwv.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kokoszkiewicz.iwv.entities.Movie;
import com.kokoszkiewicz.iwv.entities.MovieList;

public class MovieDAO {
	final static Logger logger = Logger.getLogger(MovieDAO.class);
	
    public static ArrayList<MovieList> getMovies(int recordOnPage, int productionYearFrom, int productionYearTo, String sort, int pageNumber) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	logger.info("Pobieranie bazy filmów.");
		session.beginTransaction();
		System.out.println("1: "+productionYearFrom);
		System.out.println("2: "+productionYearTo);		
		Query queryList = session.createSQLQuery(
				"CALL MovieList(:pageSize, :pageNumber, :yearFrom, :yearTo, :sort)")
				.addEntity(MovieList.class);
		queryList.setParameter("pageSize", recordOnPage);
		queryList.setParameter("pageNumber", pageNumber);
		queryList.setParameter("yearFrom", new GregorianCalendar(productionYearFrom, 1, 1).getTime());
		queryList.setParameter("yearTo", new GregorianCalendar(productionYearTo, 12, 31).getTime());
		queryList.setParameter("sort", sort);
		@SuppressWarnings("unchecked")
		ArrayList<MovieList> movies = (ArrayList<MovieList>) queryList.list();
		session.getTransaction().commit();
		logger.info("Pobieranie bazy filmów powiodło się.");
        return movies;
    }
    
    public static boolean addMovie(Movie movie, String type_name[]){
    	logger.info("Dodawanie filmu "+movie.getTitle());
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	System.out.println("Data filmu: " + movie.getPremiere_date().toString());
		session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        logger.info("Dodawanie filmu "+movie.getTitle()+" powiodło się.");
    	return true;
    }
    
    @SuppressWarnings("unchecked")
	public static Movie getMovieDetails(int id){
    	logger.info("Pobieranie szczegółów filmu o id " + id);
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createQuery("FROM Movie WHERE id_movie=:id");
		query.setParameter("id", id);
		List<Movie> list = query.list();
		session.getTransaction().commit();
		logger.info("Pobieranie szczegółów filmu o id " + id + " powiodło się.");
        return list.get(0);
    }
}
