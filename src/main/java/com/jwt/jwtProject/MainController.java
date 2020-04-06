package com.jwt.jwtProject;
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

import com.jwt.jwtProject.jwtUtil.JwtUtil;
import com.jwt.jwtProject.modals.Role;
import com.jwt.jwtProject.modals.RoleRepository;
import com.jwt.jwtProject.modals.User;
import com.jwt.jwtProject.modals.UserRepository;
import com.jwt.jwtProject.modals.UserRoleRepository;
import com.jwt.jwtProject.modals.User_Role;
import com.jwt.jwtProject.service.MyUserDetailsService;
import com.jwt.models.AuthenticationRequest;
import com.jwt.models.AuthenticationResponce;

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
	
	@RequestMapping({"/hello123"})
	public String Hello() {
		return "welcome";
	}
	
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
 