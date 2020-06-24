package com.softenza.emarket.dao;

import com.softenza.emarket.model.User;

public interface UserDao {
	public User getUser(String email, String userName, String password);
}
