package com.koseksi.pachipulusula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ShowHomeController {
	
	@RequestMapping("home")
	@ResponseBody
	public  String showHome() {
		System.out.println("hello");
		return "welcome";
	}

}
