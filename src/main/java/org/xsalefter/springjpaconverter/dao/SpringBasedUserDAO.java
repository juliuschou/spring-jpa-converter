package org.xsalefter.springjpaconverter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xsalefter.springjpaconverter.entity.User;

@Repository
public class SpringBasedUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public SpringBasedUserDAO() {}

	@Transactional(readOnly=false)
	public void create(User user) {
		this.entityManager.persist(user);
	}

	@Transactional(readOnly=true)
	public List<User> findAll() {
		return this.entityManager.createQuery("from User", User.class).getResultList();
	}

	@Transactional(readOnly=false)
	public void delete(User user) {
		if (this.entityManager.contains(user)) {
			this.entityManager.remove(user);
		} else {
			final User toDelete = this.entityManager.merge(user);
			this.entityManager.remove(toDelete);
		}
	}
}
