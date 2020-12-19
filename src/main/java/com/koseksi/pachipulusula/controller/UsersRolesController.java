package com.koseksi.pachipulusula.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User_Role;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRoleRepository;
import com.koseksi.pachipulusula.customjavabean.CustomBean;

@RestController
public class UsersRolesController {

	private static final Logger log = LoggerFactory.getLogger(UsersRolesController.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostMapping(path = "/role/create" ,consumes =  "application/json",produces =  "application/json")
	public ResponseEntity<?> createRole(@RequestBody Role role) {
		String message="";
		try {
			role.setCreated_date(new Date());
			role.setModified_date(new Date());
			role=roleRepository.save(role);
			User_Role  user_Role=new User_Role();
			user_Role.setCreated_date(new Date());
			user_Role.setModified_date(new Date());
			user_Role.setUserId(role.getUserId());
			user_Role.setRoleId(role.getRoleId());
			userRoleRepository.save(user_Role);
			message = "Role Created Successfully";
			return new ResponseEntity<>(role,HttpStatus.OK);

		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}


	@PostMapping(path = "/addRole/ToUsers/{roleId}" ,consumes =  "application/json",produces =  "application/json")
	public  ResponseEntity<?> addRoleToUsers(@PathVariable(name = "roleId")int roleId,@RequestBody List<Integer> userIds) {
		String message="Role updated to users not successfully";
		try {
			for (int userId : userIds) {
				User_Role  user_Role=new User_Role();
				user_Role.setCreated_date(new Date());
				user_Role.setModified_date(new Date());
				user_Role.setUserId(userId);
				user_Role.setRoleId(roleId);
				userRoleRepository.save(user_Role);
			}
			message = "Role updated to users Successfully";
			return new ResponseEntity<>(message,HttpStatus.OK);

		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(path = "/userRole/removeUser" ,consumes =  "application/json",produces =  "application/json")
	public ResponseEntity<?> removeUserFromUserRole(@RequestBody User_Role user_Role) {
		String message="";
		try {
			userRoleRepository.delete(user_Role);
			message = "User Removed Successfully";
			return new ResponseEntity<>(message,HttpStatus.OK);
		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/addRole/removeAllUsers" ,consumes =  "application/json",produces =  "application/json")
	public ResponseEntity<?> removeUsersFromUserRoles(@RequestBody User_Role user_Role) {
		String message="";
		try {
			userRoleRepository.delete(user_Role);
			message = "Users Removed Successfully";
			return new ResponseEntity<>(message,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping(path = "/deleteRole" ,consumes =  "application/json",produces =  "application/json")
	public ResponseEntity<?> removeUsersFromUserRoles(@RequestBody Role role) {
		String message="";
		try {
			userRoleRepository.deleteByRoleId(role.getRoleId());
			roleRepository.delete(role);
			message = "Role Removed Successfully";
			return new ResponseEntity<>(message,HttpStatus.OK);
		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
