package com.weasley.data;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDAOJPA implements UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#insert(com.centurylink.data.User)
	 */
	@Override
	public User insert(User user) {
		getEntityManager().persist(user);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#delete(com.centurylink.data.User)
	 */
	@Override
	public User delete(User user) {
		getEntityManager().remove(user);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#update(com.centurylink.data.User)
	 */
	@Override
	public User update(User user) {
		getEntityManager().merge(user);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long userNumber) {
		return getEntityManager().find(User.class, userNumber);
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return getEntityManager()
				.createQuery("select u from User u where u.userName = :userName", User.class)
				.setParameter("userName", userName)
				.getSingleResult();
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findAll()
	 */
	@Override
	public List<User> findAll() {
		return getEntityManager()
				.createQuery("select u from User u", User.class)
				.getResultList();
	}

}
