
/*
 * Roperty - An advanced property management and retrival system
 * Copyright (C) 2013 PARSHIP GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
		this.emf = Persistence.createEntityManagerFactory("roperty-ui");
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
