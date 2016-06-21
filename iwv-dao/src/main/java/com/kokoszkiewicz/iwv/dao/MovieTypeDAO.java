package com.kokoszkiewicz.iwv.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kokoszkiewicz.iwv.entities.Movie;
import com.kokoszkiewicz.iwv.entities.MovieType;
import com.kokoszkiewicz.iwv.entities.TypeOfMovie;

public class MovieTypeDAO {
	final static Logger logger = Logger.getLogger(MovieTypeDAO.class);
	
    @SuppressWarnings("unchecked")
	public static boolean addMovieTypes(Movie movie, String type_name[]){
    	logger.info("Dodawanie gatunków do filmu " + movie.getTitle());
    	Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<TypeOfMovie> list;
        Query query = session.createQuery("FROM TypeOfMovie WHERE type_name= :name ");
        MovieType movieType[] = new MovieType[type_name.length];
        for(int i=0 ; i<type_name.length ; ++i){           	
        	query.setParameter("name", type_name[i]);
        	list = query.list();
        	movieType[i]=new MovieType();
        	movieType[i].setId_type(list.get(0).getId());
        	movieType[i].setId_movie(movie.getId());
        	session.save(movieType[i]);
        }
        session.getTransaction().commit();  
        logger.info("Dodawanie gatunków do filmu " + movie.getTitle() + " powiodło się.");
        return true;
    }

}
