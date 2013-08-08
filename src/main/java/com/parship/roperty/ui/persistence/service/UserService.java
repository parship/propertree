
package com.parship.roperty.ui.persistence.service;

import com.parship.roperty.ui.persistence.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * TODO
 *
 * @author marc
 *         Since 11.07.2013
 */
public class UserService {

	private EntityManagerFactory emf;

	public UserService() {
		this.emf = Persistence.createEntityManagerFactory("ClouTree");
	}

	public User getUser(Integer id) {

		User result = null;
		EntityManager mgr = this.emf.createEntityManager();
		try {
			result = mgr.find(User.class, id);
		} finally {
			mgr.close();
		}
		return result;
	}

	public User getUserByUserName(String username) {

		User result = null;
		EntityManager mgr = this.emf.createEntityManager();
		try {
			Query query = mgr.createQuery("SELECT u FROM " + User.class.getName() + " u WHERE u.username = :username");
			query.setParameter("username", username);
			result = (User)query.getSingleResult();
		} catch (NoResultException e) {
			// TODO
			return null;
		} finally {
			mgr.close();
		}
		return result;
	}

	public boolean storeUser(User user) {

		EntityManager mgr = this.emf.createEntityManager();
		try {
			mgr.getTransaction().begin();
			mgr.persist(user);
			mgr.getTransaction().commit();
		} catch (Exception e) {
			// TODO
			return false;
		} finally {
			mgr.close();
		}
		return true;
	}

}
