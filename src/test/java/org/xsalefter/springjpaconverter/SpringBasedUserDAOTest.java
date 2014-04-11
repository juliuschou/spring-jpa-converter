package org.xsalefter.springjpaconverter;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xsalefter.springjpaconverter.dao.SpringBasedUserDAO;
import org.xsalefter.springjpaconverter.entity.Gender;
import org.xsalefter.springjpaconverter.entity.User;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath*:spring-core-config.xml")
public class SpringBasedUserDAOTest {

	@Inject
	private SpringBasedUserDAO springBasedUserDAO;
	private List<User> users;

	@Before
	public void before() {
		this.springBasedUserDAO.create(new User("User 01", Gender.MALE, "Bandung, Indonesia"));
		this.springBasedUserDAO.create(new User("User 02", Gender.FEMALE, "Jakarta, Indonesia"));
		this.springBasedUserDAO.create(new User("User 03", Gender.MALE, "Cyberjaya, Malaysia"));
		this.springBasedUserDAO.create(new User("User 04", Gender.MALE, "Singapure, Singapure"));
		this.springBasedUserDAO.create(new User("User 05", Gender.FEMALE, "New York, USA"));
	}

	@Test
	public void findAllTest() {
		this.users = this.springBasedUserDAO.findAll();
		assertThat(this.users, is(notNullValue()));
	}
}
