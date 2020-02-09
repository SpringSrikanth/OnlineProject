package com.koseksi.pachipulusula.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koseksi.pachipulusula.entity.User;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	private final static String INSERT_USER = "INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String SELECT_USER_BY_ID = "SELECT USERID,USERNAME,FIRSTNAME,LASTNAME,EMAIL,GENDER,DATEOFBIRTH,SECONDARYMAIL,MOBILE,CREATED_DATE,UPDATED_DATE FROM USERS WHERE USERID=?";
	private final static String UPDATE_USER_BY_ID = "UPDATE USERS SET USERNAME=?,FIRSTNAME=?,LASTNAME=?,EMAIL=?,GENDER=?,DATEOFBIRTH=?,SECONDARYMAIL=?,MOBILE=?,UPDATED_DATE=? WHERE USERID = ?";
	private final static String DELETE_USER_BY_ID = "DELETE FROM USERS WHERE USERID = ?";
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsersByMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveUser(User user) throws Exception {
		int value=jdbcTemplate.update(INSERT_USER, 
				user.getUserId(),
				user.getUsername(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPassword(),
				user.getGender(),
				user.getDateOfBirth(),
				user.getSecondaryMail(),
				user.getMobile(),
				user.getCreated_date(),
				user.getUpdated_date()
				);
		return value;
	}

	@Override
	public User getUserDetailsByUserId(int id) throws Exception {
		User user =null;
		System.out.println("UserDetailsDaoImpl.getUserDetailsByUserId()");
		user=jdbcTemplate.queryForObject(SELECT_USER_BY_ID, (ResultSet rs,int rowNO)->{
			User user1=new User();
			user1.setUserId(rs.getInt(1));
			user1.setUsername(rs.getString(2));
			user1.setFirstname(rs.getString(3));
			user1.setLastname(rs.getString(4));
			user1.setEmail(rs.getString(5));
			user1.setGender(rs.getString(6));
			user1.setDateOfBirth(rs.getDate(7));
			user1.setSecondaryMail(rs.getString(8));
			user1.setMobile(rs.getString(9));
			user1.setCreated_date(rs.getDate(10));
			user1.setUpdated_date(rs.getDate(11));
			return user1;
		},id);
		return user;
		
	}

	@Override
	public int updateUserDetailsByUserObj(User user) throws Exception {
		int value=jdbcTemplate.update(UPDATE_USER_BY_ID,
				user.getUsername(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getGender(),
				user.getDateOfBirth(),
				user.getSecondaryMail(),
				user.getMobile(),
				user.getUpdated_date(),
				user.getUserId()
				);
		return value;
	}
	
	@Override
	public int deleteUserDetailsByUserId(int id) throws Exception {
		return jdbcTemplate.update(DELETE_USER_BY_ID,id);
	}

}
