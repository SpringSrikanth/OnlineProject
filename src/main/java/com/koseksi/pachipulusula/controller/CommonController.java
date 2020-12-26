package com.koseksi.pachipulusula.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koseksi.app.modals.MailMessage;
import com.koseksi.app.models.CommonResponceObject;
import com.koseksi.app.repository.MessagesRepository;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

@RestController
public class CommonController {

	@Autowired
	private EncodeDecodeUtil encodeDecodeUtil;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MessagesRepository messagesRepository;

	@GetMapping(path = "/encode/encodeString/{text}", produces = "application/json")
	public CommonResponceObject getEncriptedString(@PathVariable(name = "text")String text) {
		CommonResponceObject commonResponceObject=new CommonResponceObject();
		commonResponceObject.setMessageString(encodeDecodeUtil.encodeText(text));
		commonResponceObject.setMessageType("Encription");
		return commonResponceObject;
		
	}
	
	@GetMapping(path = "/decode/decodeString/{text}", produces = "application/json")
	public CommonResponceObject getDecriptedString(@PathVariable(name = "text")String encodeText) {
		CommonResponceObject commonResponceObject=new CommonResponceObject();
		commonResponceObject.setMessageString(encodeDecodeUtil.decodeText(encodeText));
		commonResponceObject.setMessageType("Decription");
		return commonResponceObject;
		
	}
	

	@PostMapping(path = "/sentMail" ,consumes = "application/json" ,produces = "application/json")
	public ResponseEntity<?> sentNormalTextMail(@RequestBody MailMessage mailMessage){
		try {
			SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
			simpleMailMessage.setTo(mailMessage.getToAddress());
			simpleMailMessage.setSubject(mailMessage.getMessageSubject());
			simpleMailMessage.setText(mailMessage.getMessageBody());

			javaMailSender.send(simpleMailMessage);
			mailMessage.setMessageStatus("Success");
			mailMessage.setFailureMessages("No Errors");
			messagesRepository.save(mailMessage);
			return ResponseEntity.ok("Message sent successfully");

		}catch (Exception e) {
			mailMessage.setMessageStatus("Failure");
			mailMessage.setFailureMessages(e.getMessage());
			messagesRepository.save(mailMessage);
			return new ResponseEntity<>(e.getLocalizedMessage().toLowerCase(),HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/sentMailWithHtmlTemplate" ,consumes = "application/json" ,produces = "application/json")
	public ResponseEntity<?> sentMailWithHtmlTemplate(@RequestBody MailMessage mailMessage){
		try {
			MimeMessage mimeMessage= javaMailSender.createMimeMessage();

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setSubject(mailMessage.getMessageSubject());
			messageHelper.setTo(mailMessage.getToAddress());
			/* true = text/html */
			messageHelper.setText(mailMessage.getMessageBody(),true);
			javaMailSender.send(mimeMessage);
			mailMessage.setMessageStatus("Success");
			mailMessage.setFailureMessages("No Errors");
			messagesRepository.save(mailMessage);
			return ResponseEntity.ok("Message sent successfully");

		}catch (Exception e) {
			mailMessage.setMessageStatus("Failure");
			mailMessage.setFailureMessages(e.getMessage());
			messagesRepository.save(mailMessage);
			return new ResponseEntity<>(e.getLocalizedMessage().toLowerCase(),HttpStatus.BAD_REQUEST);
		}
	}

}
