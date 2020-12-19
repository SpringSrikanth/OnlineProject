package com.koseksi.pachipulusula.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ViewController {
	private static final Logger log = LoggerFactory.getLogger(ViewController.class);
	
	@RequestMapping("/home")
	public  String showHome() {
		return "welcome";
	}
}
