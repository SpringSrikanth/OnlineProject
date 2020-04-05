package com.koseksi.pachipulusula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.models.CommonResponceObject;
import com.koseksi.pachipulusula.util.EncodeDecodeUtil;

@RestController
public class CommonController {

	@Autowired
	private EncodeDecodeUtil encodeDecodeUtil;

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
}
