package com.kokoszkiewicz.iwv.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kokoszkiewicz.iwv.entities.RentedMovie;
import com.kokoszkiewicz.iwv.entities.RentedMovies;

public class RentedMovieDAO{
	final static Logger logger = Logger.getLogger(RentedMovieDAO.class);
	
    public static boolean rentMovie(RentedMovie rM) {
    	logger.info("Wypożyczanie filmu o id " + rM.getId_movie() + " przez użytkownika o id " + rM.getId_user());
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		System.out.println("id: " +rM.getId() + " id_movie: " +rM.getId_movie()+ " id_user: " + rM.getId_user() + " rent_date: " +rM.getRent_date().getTime() + " rent_end_date: " + rM.getRent_end_date().getTime());
        try{
        	session.save(rM);    
        } catch(Exception e){
        	e.printStackTrace();
        }
        session.getTransaction().commit();
    	logger.info("Wypożyczanie filmu o id " + rM.getId_movie() + " przez użytkownika o id " + rM.getId_user() + " powiodło się.");
        return true;
    }
    
    public static ArrayList<RentedMovies> getRentedMovies(String login){
    	logger.info("Pobieranie listy wypożyczonych filmów przez " + login);
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createSQLQuery(
				"CALL RentedMovies(:login)")
				.addEntity(RentedMovies.class);
		query.setParameter("login", login);
		@SuppressWarnings("unchecked")
		ArrayList<RentedMovies> rentedMovies = (ArrayList<RentedMovies>) query.list();
		session.getTransaction().commit();
		logger.info("Pobieranie listy wypożyczonych filmów przez " + login + " powiodło się.");
		return rentedMovies;
    	
    }
    
    public static boolean isRentedMovie(int user_id, int movie_id){
    	logger.info("Weryfikacja czy użytkownik o id " + user_id + " wypożyczył film o id " + movie_id);
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createQuery("FROM RentedMovie WHERE id_movie=:movie_id and id_user=:user_id");
		query.setParameter("movie_id", movie_id);
		query.setParameter("user_id", user_id);
		@SuppressWarnings("unchecked")
		List<RentedMovie> rentedMovies = query.list();
		session.getTransaction().commit();
		for(RentedMovie rm: rentedMovies) if(rm.getRent_end_date().after(new Date())){
			logger.info("Użytkownik o id " + user_id + " aktualnie jest w posiadaniu filmu o id " + movie_id);
			return true;
		}
		logger.info("Użytkownik o id " + user_id + " aktualnie nie jest w posiadaniu filmu o id " + movie_id);
    	return false;
    }

}
