package com.koseksi.pachipulusula.dao;

import java.util.List;

import com.koseksi.pachipulusula.dto.MessagesDTO;

public interface MessagesDao {

	public List<MessagesDTO> findAllMessagesByFromAndToUserID(String fromUserId, String toUserId) throws Exception;
}
