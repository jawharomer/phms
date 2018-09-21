package com.joh.phms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.UserDAO;
import com.joh.phms.model.Role;
import com.joh.phms.model.User;

@Service
public class AppUserDetailService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(AppUserDetailService.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.findByUserName(username);

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		org.springframework.security.core.userdetails.User sUser = new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getPassword(), authorities);

		logger.info("user=" + sUser);

		return (UserDetails) sUser;

	}

}