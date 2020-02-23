/**
 * 
 */
package com.koseksi.pachipulusula.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.pachipulusula.customjavabean.CustomBean;
import com.koseksi.pachipulusula.dto.UserDTO;
import com.koseksi.pachipulusula.service.UserService;

/*
 * @author Srikanth Yenagandula
 */
@RestController
@RequestMapping("users")
public class UserDetailsController {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	private UserService userService;
	
	
	@GetMapping(path="/health", produces = "application/json")
	public CustomBean getHealth() {
		return new CustomBean();
	}
	
	@GetMapping(path = "/user/{id}", produces = "application/json")
	public UserDTO getUserDetails(@PathVariable(value = "id") int valueId) {
		log.info("UserDetailsController.getUserDetails()");
		UserDTO userDto=null;
		try {
			userDto=userService.getUserDetailsByUserId(valueId);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			userDto=new UserDTO();
		}
		return userDto;
	}
	
	
	
	@PostMapping(path = "/create/user" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean storeUser(@RequestBody UserDTO userDTO) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			int value=userService.insertUser(userDTO);
			message = value>=1?"User Registration Successfully":"User Registration Fail";
			customBean.setMessage(message);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;
	}
	@PostMapping(path = "/user/update" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean updateUser(@RequestBody UserDTO userDTO) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			int value=userService.updateUserDetailsById(userDTO);
			message = value>=1?"User Updated Successfully":"User Updation Fail";
			customBean.setMessage(message);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;
	}
	
	
	@DeleteMapping(path = "/user/delete/{id}" ,produces =  "application/json")
	public CustomBean deleteUser(@PathVariable(value="id") int userId) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			int value=userService.deleteUserDetailsById(userId);
			message = value>=1?"User Deleted Successfully":"User Deletion Fail";
			customBean.setMessage(message);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;
	}
}
