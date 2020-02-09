package com.koseksi.pachipulusula.dao;

import java.util.List;

import com.koseksi.pachipulusula.entity.User;


public interface UserDetailsDao {

	public List<User> findAllUsers();
	public List<User> findAllUsersByMail();
	public int saveUser(User user) throws Exception;
	public User getUserDetailsByUserId(int id) throws Exception;
	public int updateUserDetailsByUserObj(User user) throws Exception;
	public int deleteUserDetailsByUserId(int id) throws Exception;
}
