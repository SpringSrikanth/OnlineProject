package com.koseksi.app.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.app.jwt.util.JwtUtil;
import com.koseksi.app.modals.Role;
import com.koseksi.app.modals.User;
import com.koseksi.app.modals.User_Role;
import com.koseksi.app.models.AuthenticationRequest;
import com.koseksi.app.models.AuthenticationResponce;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRepository;
import com.koseksi.app.repository.UserRoleRepository;
import com.koseksi.app.service.MyUserDetailsService;

@RestController
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
					);
			
		} catch (Exception e) {
			throw new Exception("UserName or password incorrect");
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUserName()); 
		final String jwt=jwtUtil.generateToken(userDetails);
		Optional<User> user=userRepository.findByUsername(authenticationRequest.getUserName());
		
		User user2=user.get();
		List<User_Role> user_Roles=userRoleRepository.findByUserId(user2.getUserid());
		List<Role> roles=new ArrayList<Role>();
		for (User_Role user_Role : user_Roles) {
			Optional<Role> role=roleRepository.findById(user_Role.getRoleId());
			Role role2=role.get();
			roles.add(role2);
		}
		user2.setPassword(null);
		return ResponseEntity.ok(new AuthenticationResponce(jwt,user2,roles));
	}
}
 