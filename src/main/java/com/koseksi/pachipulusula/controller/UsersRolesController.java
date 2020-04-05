package com.koseksi.pachipulusula.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtProject.modals.Role;
import com.jwt.jwtProject.modals.RoleRepository;
import com.jwt.jwtProject.modals.UserRoleRepository;
import com.jwt.jwtProject.modals.User_Role;
import com.jwt.models.CommonResponceObject;
import com.koseksi.pachipulusula.customjavabean.CustomBean;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

@RestController
public class UsersRolesController {

	private static final Logger log = LoggerFactory.getLogger(UsersRolesController.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostMapping(path = "/role/create" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean createRole(@RequestBody Role role) {
		CustomBean customBean =new CustomBean();
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
			customBean.setMessage(message);

		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;

	}


	@PostMapping(path = "/addRole/ToUsers/{roleId}" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean addRoleToUsers(@PathVariable(name = "roleId")int roleId,@RequestBody List<Integer> userIds) {
		CustomBean customBean =new CustomBean();
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
			customBean.setMessage(message);

		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;

	}


	@PostMapping(path = "/userRole/removeUser" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean removeUserFromUserRole(@RequestBody User_Role user_Role) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			userRoleRepository.delete(user_Role);
			message = "User Removed Successfully";
			customBean.setMessage(message);

		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;

	}
	@PostMapping(path = "/addRole/removeAllUsers" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean removeUsersFromUserRoles(@RequestBody User_Role user_Role) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			userRoleRepository.deleteAll();
			message = "Users Removed Successfully";
			customBean.setMessage(message);

		} catch (Exception e) {
			log.info(e.getMessage());
			customBean.setMessage(message);
			customBean.setSaatus("failure");
			customBean.setStatusCode(400);
		}
		return customBean;

	}
	
	@PostMapping(path = "/deleteRole" ,consumes =  "application/json",produces =  "application/json")
	public CustomBean removeUsersFromUserRoles(@RequestBody Role role) {
		CustomBean customBean =new CustomBean();
		String message="";
		try {
			userRoleRepository.deleteByRoleId(role.getRoleId());
			roleRepository.delete(role);
			message = "Role Removed Successfully";
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
