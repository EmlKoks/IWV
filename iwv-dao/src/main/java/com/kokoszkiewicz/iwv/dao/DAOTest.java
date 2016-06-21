package com.kokoszkiewicz.iwv.dao;

import static org.junit.Assert.*;

import com.kokoszkiewicz.iwv.entities.User;

public class DAOTest {
	private User testUser;

	public void testIsExistUser() {
		assertEquals( UserDAO.isExistUser("admin"), true);
	}
	
	public void testGetUser(){
		testUser= UserDAO.getUser("admin");
		assertEquals(testUser, testUser);
	}

}
