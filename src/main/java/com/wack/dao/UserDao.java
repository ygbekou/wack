package com.wack.dao;

import com.wack.model.User;

public interface UserDao {
	public User getUser(String email, String userName, String password);
}
