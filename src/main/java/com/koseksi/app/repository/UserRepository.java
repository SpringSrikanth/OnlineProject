package com.koseksi.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koseksi.app.modals.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
	
}
