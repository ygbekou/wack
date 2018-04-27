package com.qkcare.dao;

import com.qkcare.model.User;

public interface UserDao {
	public User getUser(String email, String userName, String password);
}
