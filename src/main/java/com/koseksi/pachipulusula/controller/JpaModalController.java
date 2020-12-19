package com.koseksi.pachipulusula.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User;
import com.koseksi.app.models.CommonResponceObject;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRepository;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

@RestController
public class JpaModalController {
	
	private static final Logger log = LoggerFactory.getLogger(JpaModalController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
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
	
	@GetMapping(path = "/user/roles" ,produces =  "application/json")
	public List<Role> getAllRoles(){
		List<Role> roles=null;
		try {
			roles=roleRepository.findAll();
		} catch (Exception e) {
			log.info(e.getMessage());
			roles=new ArrayList<Role>();
		}
		return roles;
		
	}
	
}
