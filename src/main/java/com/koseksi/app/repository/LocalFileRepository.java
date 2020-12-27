package com.koseksi.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koseksi.app.modals.LocalFileDetails;

@Repository
public interface LocalFileRepository extends JpaRepository<LocalFileDetails, String> {
	
	public Optional<LocalFileDetails> findByFileName(String fileName);

}
