package com.koseksi.pachipulusula.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.koseksi.app.models.MongoSequence;
import com.koseksi.pachipulusula.dao.MessagesDao;
import com.koseksi.pachipulusula.dto.MessagesDTO;

@Service
public class UtilService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private MessagesDao messagesDao;
	
	@SuppressWarnings("deprecation")
	public Date convertedToDateFromString(String Date){
		Date formatedDate=null;
		try {
			SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			formatedDate=sDateFormat.parse(Date);
			
		} catch(Exception e) {
			logger.info("UtilService.getDate()::Excepion");
			formatedDate=new Date(1991, 12, 12);
		}
		return formatedDate;
		
	}
	
	@SuppressWarnings("deprecation")
	public String convertedToDateToString(Date date){
		String strDate="";
		try {
			SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			strDate=sDateFormat.format(date);
			
		} catch(Exception e) {
			logger.info("UtilService.getDate()::Excepion");
			strDate="";
		}
		return strDate;
		
	}

	@SuppressWarnings("null")
	public String getNextSequenceId(String key){
		MongoSequence mongoSequence=null;
		try {
			Query query=new Query(Criteria.where(key));
			Update update=new Update();
			update.inc("seq",1);
			FindAndModifyOptions options=new FindAndModifyOptions();
			options.returnNew(true);
			mongoSequence=mongoOperations.findAndModify(query, update, options,MongoSequence.class);
			if(mongoSequence==null)
			{
				int randomNumber=new Random().nextInt(22233);
				mongoSequence.setSeq(Long.valueOf(randomNumber));
			}	
		} catch (Exception e) {
			System.out.println(e);
			mongoSequence=new MongoSequence();
			int randomNumber=new Random().nextInt(222);
			mongoSequence.setSeq(Long.valueOf(randomNumber));
		}
		
		return String.valueOf(mongoSequence.getSeq());
	}
	
	public List<MessagesDTO> findMessagesByFromAndToUserId(String fromUserId,String toUserId) throws Exception{
		return messagesDao.findAllMessagesByFromAndToUserID(fromUserId, toUserId);
		
	}
}
