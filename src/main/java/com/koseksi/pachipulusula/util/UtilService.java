package com.koseksi.pachipulusula.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
}
