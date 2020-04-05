package com.jwt.jwtProject.modals;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository< User_Role,Integer> {

	Optional<User_Role> deleteByRoleId(int roleId);
}
