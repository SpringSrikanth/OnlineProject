package com.koseksi.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koseksi.app.modals.Role;

public interface RoleRepository extends JpaRepository< Role,Integer> {

	Optional<Role> findByRoleName(String roleName);
}
