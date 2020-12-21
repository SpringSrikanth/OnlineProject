package com.koseksi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koseksi.app.modals.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, String> {

}
