package com.weasley.data;

import java.util.List;

public interface UserDAO {

	User insert(User user);

	User delete(User user);

	User update(User user);

	User findById(Long userNumber);

	User findByUserName(String userName);

	List<User> findAll();

}