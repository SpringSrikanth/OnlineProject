package com.koseksi.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.koseksi.app.modals.User_Role;

public interface UserRoleRepository extends JpaRepository< User_Role,Integer> {

	Optional<User_Role> deleteByRoleId(int roleId);
	
	List<User_Role> findByUserId(int userId);
}
