package com.weasley.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class UserDAOMock implements UserDAO {
	private static Map<Long, User> users;
	private static Map<String, List<User>> usersByUserName;
	private static Map<String, List<User>> usersByEmail;

	private static Long lastUserNumber = 0L;

	private static User addUser(User user) {
		user.setUserNumber(++lastUserNumber);
		return updateUser(user);
	}
	

	private static User updateUser(User user) {
		users.put(user.getUserNumber(), user);
		List<User> lastNameList = usersByUserName.get(user.getUserName().toLowerCase());
		if (lastNameList == null) {
			lastNameList = new Vector<>();
		}
		lastNameList.add(user);
		usersByUserName.put(user.getUserName().toLowerCase(), (lastNameList));
		List<User> emailList = usersByEmail.get(user.getEmail().toLowerCase());
		if (emailList == null) {
			emailList = new Vector<>();
		}
		emailList.add(user);
		usersByEmail.put(user.getEmail().toLowerCase(), emailList);
		return user;
	}

	public static User deleteUser(User user) {
		users.remove(user.getUserNumber());
		if (usersByUserName.get(user.getUserName().toLowerCase()) != null) {
			usersByUserName.get(user.getUserName().toLowerCase()).remove(user);
		}
		if (usersByEmail.get(user.getEmail().toLowerCase()) != null) {
			usersByEmail.get(user.getEmail().toLowerCase()).remove(user);
		}
		return user;
	}

	static {
		users = new HashMap<>();
		usersByUserName = new HashMap<>();
		usersByEmail = new HashMap<>();
		addUser(new User("hpotter", "Harry", "Potter", "harry.potter@hogwarts.ac.uk", (short)36, new Date(), new Date()));
		addUser(new User("rweasley", "Ron", "Weasley", "ron.weasley@hogwarts.ac.uk", (short)36, new Date(), new Date()));
		addUser(new User("hgranger", "Hermione", "Granger", "hermione.granger@hogwarts.ac.uk", (short)37, new Date(), new Date()));
		addUser(new User("nlongbottom", "Neville", "Longbottom", "neville.longbottom@hogwarts.ac.uk", (short)36, new Date(), new Date()));

	}

	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#insert(com.centurylink.data.User)
	 */
	@Override
	public User insert(User user) {
		return addUser(user);
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#delete(com.centurylink.data.User)
	 */
	@Override
	public User delete(User user) {
		return deleteUser(user);
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#update(com.centurylink.data.User)
	 */
	@Override
	public User update(User user) {
		return updateUser(user);
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long userNumber) {
		return users.get(userNumber);
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return usersByUserName.get(userName.toLowerCase()).get(0); // should be a unique query
	}
	
	/* (non-Javadoc)
	 * @see com.centurylink.data.UserDAO#findAll()
	 */
	@Override
	public List<User> findAll() {
		return new Vector<User>(users.values());
	}

}
