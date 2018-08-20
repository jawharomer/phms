package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.User;

public interface UserDAO extends CrudRepository<User, Integer> {

	User findByUserName(String userName);
}
