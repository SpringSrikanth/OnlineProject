package com.jwt.jwtProject.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koseksi.pachipulusula.dao.UserDetailsDao;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserDetailsDao userDetailsDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.koseksi.pachipulusula.entity.User user=null;
		try {
			user=userDetailsDao.checkUserDetails(username);
		} catch (Exception e) {
			System.out.println(e);
			throw new UsernameNotFoundException("Invalid Username and password");
		}
		return new User(user.getUsername(),user.getPassword() ,new ArrayList<>());
	}

}
