package com.koseksi.pachipulusula.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncodeDecodeUtil {

	public String encodeText(String text) {
		byte[] bytesArray=text.getBytes();
		byte[] encodedBytesArray=Base64.encodeBase64(bytesArray);
		String encodedText = new String(encodedBytesArray);
		return encodedText;
		
	}
	public String decodeText(String encodeText) {
		byte[] encodedBytesArray = encodeText.getBytes();
		byte[] decodedBytesArray = Base64.decodeBase64(encodedBytesArray);
		String decodedText = new String(decodedBytesArray);
		return decodedText;
	}
}
