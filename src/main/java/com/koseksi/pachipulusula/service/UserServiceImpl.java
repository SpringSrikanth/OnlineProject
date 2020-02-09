package com.koseksi.pachipulusula.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koseksi.pachipulusula.dao.UserDetailsDao;
import com.koseksi.pachipulusula.dto.UserDTO;
import com.koseksi.pachipulusula.entity.User;
import com.koseksi.pachipulusula.util.UtilService;


@Service("UserService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private UtilService utilService;

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	public List<User> getAllUsersByMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int  insertUser(UserDTO userDto) throws Exception {
		User user=new User();
		userDto.setCreated_date(new Date());
		userDto.setUpdated_date(new Date());
	    user.setCreated_date(userDto.getCreated_date());
	    user.setDateOfBirth(utilService.convertedToDateFromString(userDto.getDateOfBirth()));
	    user.setEmail(userDto.getEmail());
	    user.setFirstname(userDto.getFirstname());
	    user.setLastname(userDto.getLastname());
	    user.setMobile(userDto.getMobile());
	    user.setPassword(userDto.getPassword());
	    user.setSecondaryMail(userDto.getSecondaryMail());
	    user.setUpdated_date(userDto.getUpdated_date());
	    user.setUserId(userDto.getUserId());
	    user.setUsername(userDto.getUsername());
	    user.setGender(userDto.getGender());
		int k=userDetailsDao.saveUser(user);
		return k;
	}
	

	@Override
	public UserDTO getUserDetailsByUserId(int id) throws Exception {
		System.out.println("UserServiceImpl.getUserDetailsByUserId()");
		User userDetails=userDetailsDao.getUserDetailsByUserId(id);
		System.out.println(userDetails);
		UserDTO user=new UserDTO();
		user.setCreated_date(userDetails.getCreated_date());
	    user.setDateOfBirth(utilService.convertedToDateToString(userDetails.getDateOfBirth()));
	    user.setEmail(userDetails.getEmail());
	    user.setFirstname(userDetails.getFirstname());
	    user.setLastname(userDetails.getLastname());
	    user.setMobile(userDetails.getMobile());
	    user.setPassword(userDetails.getPassword());
	    user.setSecondaryMail(userDetails.getSecondaryMail());
	    user.setUpdated_date(userDetails.getUpdated_date());
	    user.setUserId(userDetails.getUserId());
	    user.setUsername(userDetails.getUsername());
	    user.setGender(userDetails.getGender());
		return user;
	}

	@Override
	public int updateUserDetailsById(UserDTO userDto) throws Exception {
		User user=new User();
		userDto.setUpdated_date(new Date());
	    user.setCreated_date(userDto.getCreated_date());
	    user.setDateOfBirth(utilService.convertedToDateFromString(userDto.getDateOfBirth()));
	    user.setEmail(userDto.getEmail());
	    user.setFirstname(userDto.getFirstname());
	    user.setLastname(userDto.getLastname());
	    user.setMobile(userDto.getMobile());
	    user.setPassword(userDto.getPassword());
	    user.setSecondaryMail(userDto.getSecondaryMail());
	    user.setUpdated_date(userDto.getUpdated_date());
	    user.setUserId(userDto.getUserId());
	    user.setUsername(userDto.getUsername());
	    user.setGender(userDto.getGender());
		return userDetailsDao.updateUserDetailsByUserObj(user);
	}
	
	@Override
	public int deleteUserDetailsById(int id) throws Exception {
		return userDetailsDao.deleteUserDetailsByUserId(id);
	}
}
