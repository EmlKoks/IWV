package com.kokoszkiewicz.iwv.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kokoszkiewicz.iwv.entities.User;

public class UserDAO{
	final static Logger logger = Logger.getLogger(UserDAO.class);
	
    public static boolean isValidLogin(User user) {
    	logger.info("Weryfikacja użytkownika " + user.getName());
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		System.out.println("Udalo sie zinicjalizowac");
		Query query = session.createQuery("from User where name = :name and password = :password");
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
        int rows = list.size();
        if (rows == 1) {
        	list.get(0).setLast_login(new Date());
        	System.out.println(list.get(0).getLast_login().toString());
        	session.save(list.get(0));
        	session.getTransaction().commit();
        	logger.info("Weryfikacja użytkownika " + user.getName() + " powiodła się.");
            return true;
        } else {
        	logger.info("Weryfikacja użytkownika " + user.getName() + " nie powiodła się.");
            return false;
        }
    }
    
    public static boolean register(User user) {
    	logger.info("Rejestracja użytkownika " + user.getName());
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		System.out.println("Udalo sie zinicjalizowac");
		System.out.println("wykonalo query:"+ user.getEmail()+ " " + user.getName());
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", user.getName());
		if(query.list().size()!=0) return false;
        session.save(user);       
        session.getTransaction().commit();
        logger.info("Rejestracja użytkownika " + user.getName() + " powiodła się.");
        return true;
    }
    
    public static User getUser(String name){
    	logger.info("Pobieranie danych użytkownika " + name);
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		System.out.println("Udalo sie zinicjalizowac");
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
        session.getTransaction().commit();
        if(list.size()==0){
        	logger.info("Pobieranie danych użytkownika " + name + " nie powiodło się.");
        	return null;
        }
        else{

        	logger.info("Pobieranie danych użytkownika " + name + " powiodło się.");
        	return list.get(0); 
        }
    }
    
    public static boolean chargeAccount(Float amount, String login){
        logger.info("Zasilanie konta użytkownika " + login);
    	User user;
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		System.out.println("Udalo sie zinicjalizowac");
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", login);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		user=list.get(0);
		user.addToBalance(amount);
        session.save(user);       
        session.getTransaction().commit();
        logger.info("Zasilanie konta użytkownika " + login + " powiodło się.");
    	return true;
    }
    
    public static boolean isExistUser(String login){
    	logger.info("Weryfikacja istnienia użytkownika " + login);
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", login);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		session.getTransaction().commit();
        System.out.print(list.toString());
        int rows = list.size();
        if (rows == 1) {
        	logger.info("Weryfikacja istnienia użytkownika " + login + " powiodła się.");
            return true;
        } else {
        	logger.info("Weryfikacja istnienia użytkownika " + login + " nie powiodła się.");
            return false;
        }   	
    }
    
    public static List<User> getUsersList(){
    	logger.info("Pobieranie listy użytkowników");
    	Session session = HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query query = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
        session.getTransaction().commit();
    	return list;
    }
	
}