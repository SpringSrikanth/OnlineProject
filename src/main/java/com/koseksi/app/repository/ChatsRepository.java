package com.koseksi.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koseksi.app.modals.Messages;

@Transactional
@Repository
public interface ChatsRepository extends JpaRepository<Messages, Integer> {

	public List<Messages> findByFromUserIdOrToUserId(int fromUserId,int toUserId);

}
