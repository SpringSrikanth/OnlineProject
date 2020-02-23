package com.koseksi.pachipulusula.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtProject.modals.User;
import com.jwt.jwtProject.modals.UserRepository;

@RestController
public class JpaModalController {
	
	private static final Logger log = LoggerFactory.getLogger(JpaModalController.class);

	@Autowired
	private UserRepository userRepository;
		
	@GetMapping(path = "/user/users" ,produces =  "application/json")
	public List<User>  getUserName(String username) {
		List<User> users=null;
		try {
			users=userRepository.findAll();
		} catch (Exception e) {
			users=new ArrayList<User>();
		}
		return users;
	}
}
