package com.koseksi.app.models;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koseksi.app.modals.Messages;
import com.koseksi.app.repository.ChatsRepository;

@Service
public class ChatSevice {
	
	@Autowired
	private ChatsRepository chatsRepository;
	
	public Messages createMessage(Messages messagesDto) throws Exception {
		return chatsRepository.save(messagesDto);		
	}
	
	public List<Messages> getAllMessages() throws Exception {
		return chatsRepository.findAll();		
	}
	
	public Optional<Messages> fetchMessage(int id) throws Exception {
		return chatsRepository.findById(id);		
	} 
	
	public String deleteMessage(int id) throws Exception {
		String message="";
		try {
			chatsRepository.deleteById(id);	
			message = "Record Deleted Successfully";
		}
		catch (Exception e) {
			throw new Exception("message not found "+id);
		}
		return message;
	} 
	
	public Messages updateMessage(Messages messagesDto) throws Exception {
		System.out.println("messageData.isPresent()");
		try {
			Optional<Messages> messageData=chatsRepository.findById(messagesDto.getMessage_Id());
			if(messageData.isPresent()) {
				System.out.println("messageData.isPresent()");
				Messages msgDto= messageData.get();
				msgDto.setMessage(messagesDto.getMessage());
				msgDto.setUpdatedDate(new Date());
				msgDto.setStatus(messagesDto.getStatus());
				return chatsRepository.save(msgDto);
			}
			else {
				System.out.println("messageData.isPresent()");
				throw new Exception("message not found "+messagesDto.getMessage_Id());
			}
		}
		catch (Exception e) {
			System.out.println("messageData.isPresent()");
			throw new Exception(e.getLocalizedMessage());
		}
	} 
	
	
	public List<Messages> fetchAllMessagesByFromUserIdToUserId(int fromUserId,int toUserId) throws Exception{
		return chatsRepository.findByFromUserIdOrToUserId(fromUserId, toUserId);
	}

}
