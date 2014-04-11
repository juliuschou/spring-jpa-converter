package org.xsalefter.springjpaconverter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.xsalefter.springjpaconverter.entity.User;

public class NonManagedUserDAO {

	private EntityManagerFactory entityManagerFactory;

	public NonManagedUserDAO(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	public void create(User user) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		entityManager.persist(user);

		entityManager.flush();
		entityManager.close();

		tx.commit();
	}

	public List<User> findAll() {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		final List<User> result = entityManager.
				createQuery("from User", User.class).
				getResultList();

		entityManager.flush();
		entityManager.close();

		tx.commit();

		return result;
	}

	public void delete(User user) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		if (entityManager.contains(user)) {
			entityManager.remove(user);
		} else {
			final User toDelete = entityManager.merge(user);
			entityManager.remove(toDelete);
		}

		entityManager.flush();
		entityManager.close();

		tx.commit();
	}
}
