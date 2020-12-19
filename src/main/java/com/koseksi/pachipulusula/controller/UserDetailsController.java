/**
 * 
 */
package com.koseksi.pachipulusula.controller;

import java.util.Date;
import java.util.Optional;

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

import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User;
import com.koseksi.app.modals.User_Role;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRepository;
import com.koseksi.app.repository.UserRoleRepository;
import com.koseksi.pachipulusula.customjavabean.CustomBean;
import com.koseksi.pachipulusula.dto.UserDTO;
import com.koseksi.pachipulusula.service.UserService;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

/*
 * @author Srikanth Yenagandula
 */
@RestController
@RequestMapping("users")
public class UserDetailsController {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private EncodeDecodeUtil encodeDecodeUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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
			
			String password=encodeDecodeUtil.encodeText(userDTO.getPassword());
			userDTO.setPassword(password);
			int value=userService.insertUser(userDTO);
			message = value>0?"User Registration Successfully":"User Registration Fail";
			if(value>=1) {
				User user=userRepository.findByUsername(userDTO.getUsername()).get();
				Role role=roleRepository.findByRoleName("USER").get();
				User_Role  user_Role=new User_Role();
				user_Role.setCreated_date(new Date());
				user_Role.setModified_date(new Date());
				user_Role.setUserId(user.getUserid());
				user_Role.setRoleId(role.getRoleId());
				userRoleRepository.save(user_Role);
			}
			customBean.setStatusCode(200);
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
			customBean.setStatusCode(200);
			
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
			customBean.setStatusCode(200);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;
	}
	
}
