package com.joh.phms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.UserDAO;
import com.joh.phms.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public User findByUserName(String userName) {
		return userDAO.findByUserName(userName);
	}

}
