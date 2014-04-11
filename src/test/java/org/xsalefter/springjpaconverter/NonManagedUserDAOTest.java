package org.xsalefter.springjpaconverter;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xsalefter.springjpaconverter.dao.NonManagedUserDAO;
import org.xsalefter.springjpaconverter.entity.Gender;
import org.xsalefter.springjpaconverter.entity.User;

public class NonManagedUserDAOTest {

	private static EntityManagerFactory entityManagerFactory;
	private NonManagedUserDAO userDAO;

	@BeforeClass
	public static void beforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("non-managed-persistence-unit");
	}

	@AfterClass
	public static void afterClass() {
		if (entityManagerFactory != null) 
			entityManagerFactory.close();
	}

	@Before
	public void before() {
		this.userDAO = new NonManagedUserDAO(entityManagerFactory);
		this.userDAO.create(new User("User 01", Gender.MALE, "Bandung, Indonesia"));
		this.userDAO.create(new User("User 02", Gender.FEMALE, "Jakarta, Indonesia"));
		this.userDAO.create(new User("User 03", Gender.MALE, "Cyberjaya, Malaysia"));
		this.userDAO.create(new User("User 04", Gender.MALE, "Singapure, Singapure"));
		this.userDAO.create(new User("User 05", Gender.FEMALE, "New York, USA"));
	}

	@Test
	public void findAllTest() {
		List<User> users = this.userDAO.findAll();
		assertThat(users, notNullValue());
	}
}
