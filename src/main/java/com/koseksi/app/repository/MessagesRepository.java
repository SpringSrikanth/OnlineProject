package com.koseksi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koseksi.app.modals.MailMessage;

@Repository
public interface MessagesRepository extends JpaRepository<MailMessage, String> {

}
