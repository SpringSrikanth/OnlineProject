package com.koseksi.pachipulusula.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User;
import com.koseksi.app.modals.User_Role;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRepository;
import com.koseksi.app.repository.UserRoleRepository;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

@RestController
@RequestMapping("/api/users/")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private EncodeDecodeUtil encodeDecodeUtil;
	
	@PostMapping(path = "/create" ,consumes = "application/json" , produces="application/json")
	public ResponseEntity<?> createUser(@RequestBody User userDetails){
		try {
			User user=userRepository.save(userDetails);
			String password=encodeDecodeUtil.encodeText(userDetails.getPassword());
			userDetails.setPassword(password);
			user.setCreated_date(new Date());
			user.setUpdated_date(new Date());
			if(user!=null) {
				User user2=userRepository.findByUsername(user.getUsername()).get();
				Role role=roleRepository.findByRoleName("USER").get();
				User_Role  user_Role=new User_Role();
				user_Role.setCreated_date(new Date());
				user_Role.setModified_date(new Date());
				user_Role.setUserId(user2.getUserid());
				user_Role.setRoleId(role.getRoleId());
				userRoleRepository.save(user_Role);
			}
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
	}
	@GetMapping(path = "/all",produces="application/json")
	public ResponseEntity<?> getAllUsers(){
		try {
			List<User> users=userRepository.findAll();
			return new ResponseEntity<>(users,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/user/{id}",produces="application/json")
	public ResponseEntity<?> getUser(@PathVariable(value = "id") int userId){
		try {
			User user=userRepository.findById(userId).orElseThrow(()-> new Exception("User Not Existed with id "+userId));
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping(path = "/update" ,consumes = "application/json" ,produces="application/json")
	public ResponseEntity<?> updateUser(@RequestBody User userDetails){
		try {
			User user=userRepository.findById(userDetails.getUserid()).orElseThrow(()-> new Exception("User Not Existed with id "+userDetails.getUserid()));
			user.setFirstname(userDetails.getFirstname());
			user.setLastname(userDetails.getLastname());
			user.setEmail(userDetails.getEmail());
			user.setMobile(userDetails.getMobile());
			user.setDateofbirth(userDetails.getDateofbirth());
			user.setSecondaryMail(userDetails.getSecondaryMail());
			user.setGender(userDetails.getGender());
			User updatedUser=userRepository.save(user);
			return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/delete/{id}",produces="application/json")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId){
		try {
			User user=userRepository.findById(userId).orElseThrow(()-> new Exception("User Not Existed with id "+userId));
			userRepository.delete(user);
			return new ResponseEntity<>("User Deleted Sucessfully",HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
