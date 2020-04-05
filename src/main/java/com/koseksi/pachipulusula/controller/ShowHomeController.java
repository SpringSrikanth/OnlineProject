package com.koseksi.pachipulusula.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ShowHomeController {
	
	private static final Logger log = LoggerFactory.getLogger(ShowHomeController.class);

	@RequestMapping("/home")
	@ResponseBody
	public  String showHome() {
	
		return "welcome";
	}

}
