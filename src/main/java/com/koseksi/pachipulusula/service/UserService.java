package com.koseksi.pachipulusula.service;

import java.util.List;

import com.koseksi.pachipulusula.dto.UserDTO;
import com.koseksi.pachipulusula.entity.User;


public interface UserService  {
	public List<User> getAllUsers();
	public List<User> getAllUsersByMail();
	public int insertUser(UserDTO userDTO) throws Exception;
	public UserDTO getUserDetailsByUserId(int userId) throws Exception;
	public int updateUserDetailsById(UserDTO user) throws Exception;
	public int deleteUserDetailsById(int id) throws Exception;
}
