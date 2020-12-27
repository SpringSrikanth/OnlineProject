package com.koseksi.pachipulusula.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koseksi.pachipulusula.dto.MessagesDTO;

@Repository
public class MessagesDaoImpl implements MessagesDao {
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	private String SELECT_MESSAGES = "SELECT * FROM chat_messages cm where  cm.from_user_id in (fromUserId,toUserId) and cm.to_user_id in (fromUserId,toUserId)  order by created_date";
	@Override
	public List<MessagesDTO> findAllMessagesByFromAndToUserID(String fromUserId, String toUserId) throws Exception {
		SELECT_MESSAGES=SELECT_MESSAGES.replace("fromUserId",fromUserId);
		SELECT_MESSAGES=SELECT_MESSAGES.replace("toUserId",toUserId);
		List<MessagesDTO> messages = jdbcTemplate.query(
				SELECT_MESSAGES,
                new BeanPropertyRowMapper<MessagesDTO>(MessagesDTO.class));

        return messages;
	}

}
