package com.kokoszkiewicz.iwv.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kokoszkiewicz.iwv.entities.TypeOfMovie;
public class TypeOfMovieDAO {
	final static Logger logger = Logger.getLogger(TypeOfMovieDAO.class);
	
	public static List<TypeOfMovie> getTypeOfMovie(){
		logger.info("Pobieranie gatunków filmów.");
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createQuery("from TypeOfMovie ORDER BY type_name");
		@SuppressWarnings("unchecked")
		List<TypeOfMovie> typeOfMovie = query.list();
		logger.info("Pobieranie gatunków filmów powiodło się.");
        return typeOfMovie;
	}
}
